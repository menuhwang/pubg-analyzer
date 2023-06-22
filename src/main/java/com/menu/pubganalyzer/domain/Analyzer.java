package com.menu.pubganalyzer.domain;

import com.menu.pubganalyzer.domain.model.LogPlayerKillV2;
import com.menu.pubganalyzer.domain.model.LogPlayerTakeDamage;
import lombok.Getter;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@ToString
public class Analyzer {
    private List<LogPlayerKillV2> logPlayerKills;
    private List<LogPlayerTakeDamage> logPlayerTakeDamages;
    private Map<String, Map<String, Float>> victimPlayerDamageDealt;
    private Map<String, List<LogPlayerTakeDamage>> victimDamageLog;
    private final Set<String> victimNames;
    private final Map<String, Integer> count = new HashMap<>();

    private Analyzer(Collection<LogPlayerKillV2> logPlayerKills, Collection<LogPlayerTakeDamage> logPlayerTakeDamages) {
        this.logPlayerKills = new ArrayList<>(logPlayerKills);
        this.logPlayerTakeDamages = new ArrayList<>(logPlayerTakeDamages);
        this.victimNames = LogPlayerKillV2.extractVictimNames(logPlayerKills);
        count.put("player", 0);
        count.put("bot", 0);
    }

    public static Analyzer of(Collection<LogPlayerKillV2> logPlayerKills, Collection<LogPlayerTakeDamage> logPlayerTakeDamages) {
        return new Analyzer(logPlayerKills, logPlayerTakeDamages).analyze();
    }

    public static Analyzer of(Telemetry telemetry, String killer) {
        return new Analyzer(telemetry.getLogPlayerKills(), telemetry.getLogPlayerTakeDamages())
                .analyze()
                .filterByKiller(killer);
    }

    private Analyzer analyze() {
        damagesGroupByVictim();
        calculateDamageDealt();

        return this;
    }

    private void damagesGroupByVictim() {
        if (victimDamageLog == null) victimDamageLog = new HashMap<>();

        for (LogPlayerTakeDamage logPlayerTakeDamage : logPlayerTakeDamages) {
            if (!victimNames.contains(logPlayerTakeDamage.getVictimName())) continue;

            String vitim = logPlayerTakeDamage.getVictimName();

            List<LogPlayerTakeDamage> victimDamageLogValue = victimDamageLog.getOrDefault(vitim, new ArrayList<>());
            victimDamageLogValue.add(logPlayerTakeDamage);
            victimDamageLog.put(vitim, victimDamageLogValue);
        }
    }

    private void calculateDamageDealt() {
        if (victimPlayerDamageDealt == null) victimPlayerDamageDealt = new HashMap<>();

        for (LogPlayerTakeDamage logPlayerTakeDamage : logPlayerTakeDamages) {
            if (!victimNames.contains(logPlayerTakeDamage.getVictimName())) continue;

            String vitim = logPlayerTakeDamage.getVictimName();
            String attacker = logPlayerTakeDamage.getAttackerName();

            Map<String, Float> attackerDamageDealt = victimPlayerDamageDealt.getOrDefault(vitim, new HashMap<>());
            float damageDealt = attackerDamageDealt.getOrDefault(attacker, 0F);
            damageDealt += logPlayerTakeDamage.getDamage();
            attackerDamageDealt.put(attacker, damageDealt);
            victimPlayerDamageDealt.put(vitim, attackerDamageDealt);
        }
    }


    public Analyzer filterByKiller(String nickname) {
        List<LogPlayerKillV2> filteredLogPlayerKills = this.logPlayerKills.stream()
                .filter(log -> log.getKillerName() != null && log.getKillerName().equals(nickname))
                .collect(Collectors.toList());

        List<LogPlayerTakeDamage> filteredLogPlayerTakeDamages = this.logPlayerTakeDamages.stream()
                .filter(logPlayerTakeDamage -> nickname.equals(logPlayerTakeDamage.getAttackerName()))
                .collect(Collectors.toList());

        this.logPlayerKills = filteredLogPlayerKills;
        this.logPlayerTakeDamages = filteredLogPlayerTakeDamages;

        countKillsPlayerBot();

        return this;
    }

    private void countKillsPlayerBot() {
        for (LogPlayerKillV2 logPlayerKill : logPlayerKills) {
            String victimType = logPlayerKill.getVictimAccountId().startsWith("ai") ? "bot" : "player";
            count.put(victimType, count.get(victimType) + 1);
        }
    }
}
