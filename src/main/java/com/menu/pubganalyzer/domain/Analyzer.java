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
    private final List<LogPlayerKillV2> logPlayerKills;
    private final List<LogPlayerTakeDamage> logPlayerTakeDamages;
    private Map<String, Map<String, Float>> victimPlayerDamageDealt;
    private Map<String, List<LogPlayerTakeDamage>> victimDamageLog;

    private Analyzer(Collection<LogPlayerKillV2> logPlayerKills, Collection<LogPlayerTakeDamage> logPlayerTakeDamages) {
        this.logPlayerKills = new ArrayList<>(logPlayerKills);
        this.logPlayerTakeDamages = new ArrayList<>(logPlayerTakeDamages);
    }

    public static Analyzer of(Collection<LogPlayerKillV2> logPlayerKills, Collection<LogPlayerTakeDamage> logPlayerTakeDamages) {
        return new Analyzer(logPlayerKills, logPlayerTakeDamages).analyze();
    }

    public static Analyzer of(Telemetry telemetry) {
        return new Analyzer(telemetry.getLogPlayerKills(), telemetry.getLogPlayerTakeDamages()).analyze();
    }

    public static Analyzer analyzeOf(Set<String> memberNames, List<LogPlayerKillV2> logPlayerKills, List<LogPlayerTakeDamage> logPlayerTakeDamages) {
        logPlayerKills = logPlayerKills.stream()
                .filter(log -> log.getKillerName() != null && memberNames.contains(log.getKillerName()))
                .collect(Collectors.toList());

        List<String> victimNames = LogPlayerKillV2.extractVictimNames(logPlayerKills);

        logPlayerTakeDamages = logPlayerTakeDamages.stream()
                .filter(log -> victimNames.contains(log.getVictimName()) && memberNames.contains(log.getAttackerName()))
                .collect(Collectors.toList());

        return Analyzer.of(logPlayerKills, logPlayerTakeDamages);
    }

    private Analyzer analyze() {
        victimPlayerDamageDealt = new HashMap<>();
        victimDamageLog = new HashMap<>();
        for (LogPlayerTakeDamage log : logPlayerTakeDamages) {
            String vitim = log.getVictimName();
            String attacker = log.getAttackerName();

            List<LogPlayerTakeDamage> victimDamageLogValue = victimDamageLog.getOrDefault(vitim, new ArrayList<>());
            victimDamageLogValue.add(log);
            victimDamageLog.put(vitim, victimDamageLogValue);

            Map<String, Float> attackerDamageDealt = victimPlayerDamageDealt.getOrDefault(vitim, new HashMap<>());
            float damageDealt = attackerDamageDealt.getOrDefault(attacker, 0F);
            damageDealt += log.getDamage();
            attackerDamageDealt.put(attacker, damageDealt);
            victimPlayerDamageDealt.put(vitim, attackerDamageDealt);
        }

        return this;
    }

    public Analyzer filterOfKiller(String nickname) {
        List<LogPlayerKillV2> filteredLogPlayerKills = this.logPlayerKills.stream()
                .filter(log -> log.getKillerName() != null && log.getKillerName().equals(nickname))
                .collect(Collectors.toList());

        Set<String> victims = new HashSet<>(LogPlayerKillV2.extractVictimNames(filteredLogPlayerKills));

        List<LogPlayerTakeDamage> filteredLogPlayerTakeDamages = this.logPlayerTakeDamages.stream()
                .filter(logPlayerTakeDamage -> victims.contains(logPlayerTakeDamage.getVictimName()))
                .collect(Collectors.toList());

        return Analyzer.of(filteredLogPlayerKills, filteredLogPlayerTakeDamages);
    }
}
