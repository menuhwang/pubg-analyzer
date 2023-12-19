package com.menu.pubganalyzer.util;

import com.menu.pubganalyzer.telemetries.dto.response.BarChartDataset;
import com.menu.pubganalyzer.telemetries.dto.response.ContributeDamageChartResponse;
import com.menu.pubganalyzer.telemetries.dto.response.PhaseDamageChartResponse;
import com.menu.pubganalyzer.util.pubg.response.telemetry.events.LogPlayerKillV2;
import com.menu.pubganalyzer.util.pubg.response.telemetry.events.LogPlayerTakeDamage;
import com.menu.pubganalyzer.util.pubg.response.telemetry.objects.CharacterResponse;

import java.util.*;
import java.util.stream.Collectors;

public class ChartUtil {
    private static final int PHASE_SIZE = 10; // 0 ~ 9

    public static PhaseDamageChartResponse phaseDamageChart(final List<LogPlayerTakeDamage> logPlayerTakeDamages) {
        float[] phaseDamageDealt = new float[PHASE_SIZE];

        for (LogPlayerTakeDamage logPlayerTakeDamage : logPlayerTakeDamages) {
            int phase = logPlayerTakeDamage.getPhase();
            phaseDamageDealt[phase] += (float) logPlayerTakeDamage.getDamage();
        }

        return new PhaseDamageChartResponse(phaseDamageDealt);
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

        LinkedList<BarChartDataset<Float>> datasets = new LinkedList<>();
        for (String member : members) {
            Float[] data = new Float[victims.size()];
            Arrays.fill(data, 0F);
            for (int i = 0; i < data.length; i++) {
                data[i] = victimPlayerDamageDealt.get(victims.get(i)).getOrDefault(member, 0F);
            }

            datasets.add(new BarChartDataset<>(member, data));
        }

        Iterator<BarChartDataset<Float>> iterator = datasets.iterator();
        while (iterator.hasNext()) {
            BarChartDataset<Float> data = iterator.next();
            if (player.equals(data.getLabel())) { // 조회한 유저의 데이터를 가장 앞으로 이동한다.
                iterator.remove();
                datasets.addFirst(data);
                break;
            }
        }

        return new ContributeDamageChartResponse(victims, datasets);
    }
}
