package com.menu.pubganalyzer.util;

import com.menu.pubganalyzer.telemetries.dto.response.ContributeDamageChartDataset;
import com.menu.pubganalyzer.telemetries.dto.response.ContributeDamageChartRes;
import com.menu.pubganalyzer.telemetries.dto.response.PhaseDamageChartRes;
import com.menu.pubganalyzer.telemetries.model.LogPlayerKillV2;
import com.menu.pubganalyzer.telemetries.model.LogPlayerTakeDamage;

import java.util.*;
import java.util.stream.Collectors;

public class ChartUtil {
    private static final int PHASE_SIZE = 10; // 0 ~ 9

    public static PhaseDamageChartRes phaseDamageChart(final List<LogPlayerTakeDamage> logPlayerTakeDamages) {
        float[] phaseDamageDealt = new float[PHASE_SIZE];

        for (LogPlayerTakeDamage logPlayerTakeDamage : logPlayerTakeDamages) {
            int phase = logPlayerTakeDamage.getPhase();
            phaseDamageDealt[phase] += logPlayerTakeDamage.getDamage();
        }

        return new PhaseDamageChartRes(phaseDamageDealt);
    }

    public static ContributeDamageChartRes contributeDamageChart(
            final String player,
            final Set<String> members,
            final List<LogPlayerKillV2> logPlayerKills,
            final List<LogPlayerTakeDamage> logPlayerTakeDamages) {
        List<String> victims = logPlayerKills.stream()
                .map(LogPlayerKillV2::getVictimName)
                .collect(Collectors.toList());

        Map<String, Map<String, Float>> victimPlayerDamageDealt = new HashMap<>();

        for (LogPlayerTakeDamage logPlayerTakeDamage : logPlayerTakeDamages) {
            if (!victims.contains(logPlayerTakeDamage.getVictimName())) continue;

            String vitim = logPlayerTakeDamage.getVictimName();
            String attacker = logPlayerTakeDamage.getAttackerName();

            Map<String, Float> attackerDamageDealt = victimPlayerDamageDealt.getOrDefault(vitim, new HashMap<>());
            float damageDealt = attackerDamageDealt.getOrDefault(attacker, 0F);
            damageDealt += logPlayerTakeDamage.getDamage();
            attackerDamageDealt.put(attacker, damageDealt);
            victimPlayerDamageDealt.put(vitim, attackerDamageDealt);
        }

        LinkedList<ContributeDamageChartDataset> datasets = new LinkedList<>();
        for (String member : members) {
            float[] data = new float[victims.size()];
            for (int i = 0; i < data.length; i++) {
                data[i] = victimPlayerDamageDealt.get(victims.get(i)).getOrDefault(member, 0F);
            }

            datasets.add(new ContributeDamageChartDataset(member, data));
        }

        Iterator<ContributeDamageChartDataset> iterator = datasets.iterator();
        while (iterator.hasNext()) {
            ContributeDamageChartDataset data = iterator.next();
            if (player.equals(data.getLabel())) { // 조회한 유저의 데이터를 가장 앞으로 이동한다.
                iterator.remove();
                datasets.addFirst(data);
                break;
            }
        }

        return new ContributeDamageChartRes(victims, datasets);
    }
}
