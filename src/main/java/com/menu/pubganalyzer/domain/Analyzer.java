package com.menu.pubganalyzer.domain;

import com.menu.pubganalyzer.domain.model.LogPlayerKillV2;
import com.menu.pubganalyzer.domain.model.LogPlayerTakeDamage;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Analyzer {
    private List<LogPlayerKillV2> logPlayerKills;
    private List<LogPlayerTakeDamage> logPlayerTakeDamages;
    private Map<String, Map<String, Float>> victimPlayerDamageDealt;
    private Map<String, List<LogPlayerTakeDamage>> victimDamageLog;

    public Analyzer(Collection<LogPlayerKillV2> logPlayerKills, Collection<LogPlayerTakeDamage> logPlayerTakeDamages) {
        this.logPlayerKills = new ArrayList<>(logPlayerKills);
        this.logPlayerTakeDamages = new ArrayList<>(logPlayerTakeDamages);
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
    }

    public static Analyzer of(Collection<LogPlayerKillV2> logPlayerKills, Collection<LogPlayerTakeDamage> logPlayerTakeDamages) {
        return new Analyzer(logPlayerKills, logPlayerTakeDamages);
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

    public void filterOfKillName(String nickname) {
        this.logPlayerKills = logPlayerKills.stream()
                .filter(log -> log.getKillerName() != null && log.getKillerName().equals(nickname))
                .collect(Collectors.toList());

        Set<String> kills = new HashSet<>(LogPlayerKillV2.extractVictimNames(logPlayerKills));

        Set<String> victims = new HashSet<>(this.victimPlayerDamageDealt.keySet());

        victims.removeAll(kills);

        for (String victim : victims) {
            this.victimPlayerDamageDealt.remove(victim);
            this.victimDamageLog.remove(victim);
        }
    }
}
