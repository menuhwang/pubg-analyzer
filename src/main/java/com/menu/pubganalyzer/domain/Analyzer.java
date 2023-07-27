package com.menu.pubganalyzer.domain;

import com.menu.pubganalyzer.domain.model.telemetries.Character;
import com.menu.pubganalyzer.domain.model.telemetries.LogPlayerKillV2;
import com.menu.pubganalyzer.domain.model.telemetries.LogPlayerTakeDamage;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@ToString
public class Analyzer {
    private List<LogPlayerKillV2> logPlayerKills;
    private Set<String> victimNames;
    private List<LogPlayerTakeDamage> rosterLogPlayerTakeDamages;
    private List<LogPlayerTakeDamage> totalLogPlayerTakeDamages;
    private Map<String, Map<String, Float>> victimPlayerDamageDealt;
    private Map<String, List<LogPlayerTakeDamage>> victimDamageLog;
    private Map<String, Integer> count;
    private Analyzer() {}

    public static Analyzer init() {
        return new Analyzer();
    }

    public Analyzer logPlayerKills(List<LogPlayerKillV2> logPlayerKills) {
        this.logPlayerKills = logPlayerKills;
        this.victimNames = logPlayerKills.stream()
                .map(LogPlayerKillV2::getVictim)
                .map(Character::getName)
                .collect(Collectors.toSet());

        return this;
    }

    public Analyzer rosterLogPlayerTakeDamages(List<LogPlayerTakeDamage> logPlayerTakeDamages) {
        this.rosterLogPlayerTakeDamages = logPlayerTakeDamages;

        return this;
    }

    public Analyzer totalLogPlayerTakeDamage(List<LogPlayerTakeDamage> logPlayerTakeDamages) {
        this.totalLogPlayerTakeDamages = logPlayerTakeDamages;

        return this;
    }

    public Map<String, Map<String, Float>> analysisDamageDealt() {
        if (Objects.nonNull(victimPlayerDamageDealt)) return victimPlayerDamageDealt;
        calculateDamageDealt();
        return victimPlayerDamageDealt;
    }

    public Map<String, List<LogPlayerTakeDamage>> damageLogGroupByVictim() {
        if (Objects.nonNull(victimDamageLog)) return victimDamageLog;
        damagesGroupByVictim();
        return victimDamageLog;
    }

    public Map<String, Integer> countVictimType() {
        if (Objects.nonNull(count)) return count;
        countKillsPlayerBot();
        return count;
    }

    private void damagesGroupByVictim() {
        if (Objects.isNull(rosterLogPlayerTakeDamages) || Objects.isNull(victimNames)) throw new RuntimeException("데미지 로그 정리를 진행할 수 없습니다.");
        victimDamageLog = new HashMap<>();

        for (LogPlayerTakeDamage logPlayerTakeDamage : rosterLogPlayerTakeDamages) {
            if (!victimNames.contains(logPlayerTakeDamage.getVictimName())) continue;

            String vitim = logPlayerTakeDamage.getVictimName();

            List<LogPlayerTakeDamage> victimDamageLogValue = victimDamageLog.getOrDefault(vitim, new ArrayList<>());
            victimDamageLogValue.add(logPlayerTakeDamage);
            victimDamageLog.put(vitim, victimDamageLogValue);
        }
    }

    private void calculateDamageDealt() {
        if (Objects.isNull(victimNames) || Objects.isNull(rosterLogPlayerTakeDamages)) throw new RuntimeException("데미지 계산을 진행할 수 없습니다.");
        victimPlayerDamageDealt = new HashMap<>();

        for (LogPlayerTakeDamage logPlayerTakeDamage : rosterLogPlayerTakeDamages) {
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

    private void countKillsPlayerBot() {
        if (Objects.isNull(logPlayerKills)) throw new RuntimeException("봇 수를 계산할 수 없습니다.");

        count = countInit();

        for (LogPlayerKillV2 logPlayerKill : logPlayerKills) {
            String victimType = logPlayerKill.isVictimBot() ? "bot" : "player";
            count.put(victimType, count.get(victimType) + 1);
        }
    }

    private Map<String, Integer> countInit() {
        Map<String, Integer> init = new HashMap<>();
        init.put("bot", 0);
        init.put("player", 0);

        return init;
    }

    public List<LogPlayerKillV2> getLogPlayerKills() {
        if (Objects.isNull(logPlayerKills)) return Collections.emptyList();
        return logPlayerKills;
    }

    public List<LogPlayerTakeDamage> getRosterLogPlayerTakeDamages() {
        if (Objects.isNull(rosterLogPlayerTakeDamages)) return Collections.emptyList();
        return rosterLogPlayerTakeDamages;
    }

    public Set<String> getVictimNames() {
        return Objects.requireNonNull(victimNames);
    }

    public List<LogPlayerTakeDamage> getTotalLogPlayerTakeDamages() {
        if (Objects.isNull(totalLogPlayerTakeDamages)) return Collections.emptyList();
        return totalLogPlayerTakeDamages;
    }
}
