package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.model.LogPlayerTakeDamage;
import com.menu.pubganalyzer.domain.model.enums.DamageCauserName;
import com.menu.pubganalyzer.domain.model.enums.DamageReason;
import com.menu.pubganalyzer.domain.model.enums.DamageTypeCategory;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DamageLogRes {
    private final LocalDateTime timestamp;
    private final CharacterInfo attacker;
    private final CharacterInfo victim;
    private final DamageTypeCategory damageTypeCategory;
    private final DamageReason damageReason;
    private final Float damage;
    private final DamageCauserName damageCauserName;

    @Builder
    public DamageLogRes(LocalDateTime timestamp, CharacterInfo attacker, CharacterInfo victim, DamageTypeCategory damageTypeCategory, DamageReason damageReason, Float damage, DamageCauserName damageCauserName) {
        this.timestamp = timestamp;
        this.attacker = attacker;
        this.victim = victim;
        this.damageTypeCategory = damageTypeCategory;
        this.damageReason = damageReason;
        this.damage = damage;
        this.damageCauserName = damageCauserName;
    }

    public static DamageLogRes of(LogPlayerTakeDamage logPlayerTakeDamage) {
        CharacterInfo attacker = CharacterInfo.builder()
                .name(logPlayerTakeDamage.getAttackerName())
                .teamId(logPlayerTakeDamage.getAttackerTeamId())
                .health(logPlayerTakeDamage.getAttackerHealth())
                .ranking(logPlayerTakeDamage.getAttackerRanking())
                .accountId(logPlayerTakeDamage.getAttackerAccountId())
                .build();

        CharacterInfo victim = CharacterInfo.builder()
                .name(logPlayerTakeDamage.getVictimName())
                .teamId(logPlayerTakeDamage.getVictimTeamId())
                .health(logPlayerTakeDamage.getVictimHealth())
                .ranking(logPlayerTakeDamage.getVictimRanking())
                .accountId(logPlayerTakeDamage.getVictimAccountId())
                .build();

        return DamageLogRes.builder()
                .timestamp(logPlayerTakeDamage.getTimestamp())
                .attacker(attacker)
                .victim(victim)
                .damageTypeCategory(logPlayerTakeDamage.getDamageTypeCategory())
                .damageReason(logPlayerTakeDamage.getDamageReason())
                .damage(logPlayerTakeDamage.getDamage())
                .damageCauserName(logPlayerTakeDamage.getDamageCauserName())
                .build();
    }

    @Getter
    public static class CharacterInfo {
        String name;
        Integer teamId;
        Float health;
        Integer ranking;
        String accountId;

        @Builder
        public CharacterInfo(String name, Integer teamId, Float health, Integer ranking, String accountId) {
            this.name = name;
            this.teamId = teamId;
            this.health = health;
            this.ranking = ranking;
            this.accountId = accountId;
        }
    }
}
