package com.menu.pubganalyzer.util;

import com.menu.pubganalyzer.telemetries.dto.response.ContributeDamageChartResponse;
import com.menu.pubganalyzer.telemetries.dto.response.PhaseDamageChartResponse;
import com.menu.pubganalyzer.telemetries.dto.response.WeaponAccuracyChartResponse;
import com.menu.pubganalyzer.telemetries.dto.response.enums.Shotgun;
import com.menu.pubganalyzer.telemetries.dto.response.enums.Weapon;
import com.menu.pubganalyzer.util.chartjs.Dataset;
import com.menu.pubganalyzer.util.pubg.response.telemetry.events.LogPlayerAttack;
import com.menu.pubganalyzer.util.pubg.response.telemetry.events.LogPlayerKillV2;
import com.menu.pubganalyzer.util.pubg.response.telemetry.events.LogPlayerTakeDamage;
import com.menu.pubganalyzer.util.pubg.response.telemetry.objects.CharacterResponse;

import java.util.*;
import java.util.stream.Collectors;

public class ChartUtil {
    private static final int PHASE_SIZE = 10; // 0 ~ 9

    public static PhaseDamageChartResponse phaseDamageChart(final List<LogPlayerTakeDamage> logPlayerTakeDamages) {
        List<String> labels = new ArrayList<>();
        List<Float> phaseDamageDealt = new ArrayList<>();

        for (int i = 0; i < PHASE_SIZE; i++) {
            labels.add(i + " 페이즈");
            phaseDamageDealt.add(0F);
        }

        for (LogPlayerTakeDamage logPlayerTakeDamage : logPlayerTakeDamages) {
            int phase = logPlayerTakeDamage.getPhase();
            float damage = (float) logPlayerTakeDamage.getDamage();
            phaseDamageDealt.set(phase, phaseDamageDealt.get(phase) + damage);
        }

        Dataset dataset = new Dataset("", phaseDamageDealt);

        return new PhaseDamageChartResponse(labels, List.of(dataset));
    }

    public static ContributeDamageChartResponse contributeDamageChart(
            final String player,
            final Set<String> members,
            final List<LogPlayerKillV2> logPlayerKills,
            final List<LogPlayerTakeDamage> logPlayerTakeDamages) {
        List<String> victims = logPlayerKills.stream()
                .map(LogPlayerKillV2::getVictim)
                .map(CharacterResponse::getName)
                .collect(Collectors.toList());

        Map<String, Map<String, Float>> victimPlayerDamageDealt = new HashMap<>();

        for (LogPlayerTakeDamage logPlayerTakeDamage : logPlayerTakeDamages) {
            if (!victims.contains(logPlayerTakeDamage.getVictim().getName())) continue;

            String vitim = logPlayerTakeDamage.getVictim().getName();
            String attacker = logPlayerTakeDamage.getAttacker().getName();

            Map<String, Float> attackerDamageDealt = victimPlayerDamageDealt.getOrDefault(vitim, new HashMap<>());
            float damageDealt = attackerDamageDealt.getOrDefault(attacker, 0F);
            damageDealt += (float) logPlayerTakeDamage.getDamage();
            attackerDamageDealt.put(attacker, damageDealt);
            victimPlayerDamageDealt.put(vitim, attackerDamageDealt);
        }

        LinkedList<Dataset> datasets = new LinkedList<>();
        for (String member : members) {
            List<Float> data = new ArrayList<>();
            for (int i = 0; i < victims.size(); i++) {
                data.add(0F);
            }
            for (int i = 0; i < victims.size(); i++) {
                data.set(i, victimPlayerDamageDealt.get(victims.get(i)).getOrDefault(member, 0F));
            }

            datasets.add(new Dataset(member, data));
        }

        Iterator<Dataset> iterator = datasets.iterator();
        while (iterator.hasNext()) {
            Dataset data = iterator.next();
            if (player.equals(data.getLabel())) { // 조회한 유저의 데이터를 가장 앞으로 이동한다.
                iterator.remove();
                datasets.addFirst(data);
                break;
            }
        }

        return new ContributeDamageChartResponse(victims, datasets);
    }

    // 타격할 수 없는 보조 무기
    private static final Set<String> SUPPORT_WEAPONS = Set.of("Item_Weapon_M79_C", "Item_Weapon_StunGun_C", "Item_Weapon_FlareGun_C");

    /**
     * @param playerAttacks 'telemetry.weapon.subCategory'가 ['Main', 'Handgun']인 데이터가 필요합니다. SUPPORT_WEAPONS를 제외한 데이터를 넘겨주길 권장합니다.
     * @param playerTakeDamages 'telemetry.attacker.name'과 'telemetry.victim.name'가 다른 데이터가 필요합니다.
     * @return
     */
    public static WeaponAccuracyChartResponse weaponAccuracyChart(
            final List<LogPlayerAttack> playerAttacks,
            final List<LogPlayerTakeDamage> playerTakeDamages) {
        Set<String> firedWeapons = new LinkedHashSet<>();
        Map<String, Integer> fire = new HashMap<>();
        for (LogPlayerAttack playerAttack : playerAttacks) {
            String itemId = playerAttack.getWeapon().getItemId();
            if (SUPPORT_WEAPONS.contains(itemId))
                continue;
            String weapon = itemId.replaceAll("Item_Weapon_", "Weap");
            fire.compute(weapon, (k, v) -> v == null ? playerAttack.getFireWeaponStackCount() : Math.max(v, playerAttack.getFireWeaponStackCount()));
            firedWeapons.add(weapon);
        }

        Map<String, Integer> hit = new HashMap<>();
        for (LogPlayerTakeDamage playerTakeDamage : playerTakeDamages) {
            if (!firedWeapons.contains(playerTakeDamage.getDamageCauserName()))
                continue;
            String weapon = playerTakeDamage.getDamageCauserName();
            hit.compute(weapon, (k, v) -> v == null ? 1 : v + 1);
        }

        List<String> labels = new ArrayList<>();
        List<Integer> fireData = new ArrayList<>();
        List<Integer> hitData = new ArrayList<>();

        for (String firedWeapon : firedWeapons) {
            Weapon weapon = Weapon.of(firedWeapon);

            int f = fire.get(firedWeapon);

            // 샷건 (탄환 수 / 격발) 계산
            if (Shotgun.contains(weapon)) {
                Shotgun shotgun = Shotgun.of(weapon);
                f *= shotgun.getAmmo();
            }

            fireData.add(f);

            int h = hit.getOrDefault(firedWeapon, 0);
            hitData.add(h);

            labels.add(String.format("%s (%d%%)", weapon.getEng(), 100 * h / f));
        }

        Dataset fireDataset = new Dataset("fire", fireData);
        Dataset hitDataset = new Dataset("hit", hitData);

        return new WeaponAccuracyChartResponse(labels, List.of(fireDataset, hitDataset));
    }
}
