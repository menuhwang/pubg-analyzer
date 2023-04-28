package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.model.LogPlayerKillV2;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class KillLogRes {
    private final LocalDateTime timestamp;
    private final CharacterInfo victim;
    private final CharacterInfo dBNOMaker;
    private final DamageInfo dBNOInfo;
    private final CharacterInfo finisher;
    private final DamageInfo finishInfo;
    private final CharacterInfo killer;
    private final DamageInfo killInfo;
    private final Boolean suicide;

    @Builder
    public KillLogRes(LocalDateTime timestamp, CharacterInfo victim, CharacterInfo dBNOMaker, DamageInfo dBNOInfo, CharacterInfo finisher, DamageInfo finishInfo, CharacterInfo killer, DamageInfo killInfo, Boolean suicide) {
        this.timestamp = timestamp;
        this.victim = victim;
        this.dBNOMaker = dBNOMaker;
        this.dBNOInfo = dBNOInfo;
        this.finisher = finisher;
        this.finishInfo = finishInfo;
        this.killer = killer;
        this.killInfo = killInfo;
        this.suicide = suicide;
    }

    public static KillLogRes of(LogPlayerKillV2 logPlayerKillV2) {
        CharacterInfo victim = CharacterInfo.builder()
                .name(logPlayerKillV2.getVictimName())
                .accountId(logPlayerKillV2.getVictimAccountId())
                .build();
        CharacterInfo dBNOMaker = CharacterInfo.builder()
                .name(logPlayerKillV2.getDBNOName())
                .accountId(logPlayerKillV2.getDBNOAccountId())
                .build();
        CharacterInfo finisher = CharacterInfo.builder()
                .name(logPlayerKillV2.getFinisherName())
                .accountId(logPlayerKillV2.getFinisherAccountId())
                .build();
        CharacterInfo killer = CharacterInfo.builder()
                .name(logPlayerKillV2.getKillerName())
                .accountId(logPlayerKillV2.getKillerAccountId())
                .build();

        DamageInfo dBNOInfo = DamageInfo.builder()
                .damageReason(logPlayerKillV2.getDBNODamageReason())
                .damageTypeCategory(logPlayerKillV2.getDBNODamageTypeCategory())
                .damageCauserName(logPlayerKillV2.getDBNODamageCauserName())
                .distance(logPlayerKillV2.getDBNODistance())
                .build();
        DamageInfo finishInfo = DamageInfo.builder()
                .damageReason(logPlayerKillV2.getFinisherDamageReason())
                .damageTypeCategory(logPlayerKillV2.getFinisherDamageTypeCategory())
                .damageCauserName(logPlayerKillV2.getFinisherDamageCauserName())
                .distance(logPlayerKillV2.getFinisherDistance())
                .build();
        DamageInfo killInfo = DamageInfo.builder()
                .damageReason(logPlayerKillV2.getKillerDamageReason())
                .damageTypeCategory(logPlayerKillV2.getKillerDamageTypeCategory())
                .damageCauserName(logPlayerKillV2.getKillerDamageCauserName())
                .distance(logPlayerKillV2.getKillerDistance())
                .build();

        return KillLogRes.builder()
                .timestamp(logPlayerKillV2.getTimestamp())
                .victim(victim)
                .dBNOMaker(dBNOMaker)
                .dBNOInfo(dBNOInfo)
                .finisher(finisher)
                .finishInfo(finishInfo)
                .killer(killer)
                .killInfo(killInfo)
                .suicide(logPlayerKillV2.getSuicide())
                .build();
    }

    @Getter
    public static class CharacterInfo {
        String name;
        String accountId;

        @Builder
        public CharacterInfo(String name, String accountId) {
            this.name = name;
            this.accountId = accountId;
        }
    }

    @Getter
    public static class DamageInfo {
        private String damageReason; // enum
        private String damageTypeCategory; // enum
        private String damageCauserName; // enum
        private Float distance;

        @Builder
        public DamageInfo(String damageReason, String damageTypeCategory, String damageCauserName, Float distance) {
            this.damageReason = damageReason;
            this.damageTypeCategory = damageTypeCategory;
            this.damageCauserName = damageCauserName;
            this.distance = distance;
        }
    }
}
