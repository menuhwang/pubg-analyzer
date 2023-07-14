package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.model.LogPlayerKillV2;
import com.menu.pubganalyzer.domain.model.enums.DamageCauserName;
import com.menu.pubganalyzer.domain.model.enums.DamageReason;
import com.menu.pubganalyzer.domain.model.enums.DamageTypeCategory;
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
                .bot(logPlayerKillV2.isVictimBot())
                .build();
        CharacterInfo dBNOMaker = CharacterInfo.builder()
                .name(logPlayerKillV2.getDBNOName())
                .accountId(logPlayerKillV2.getDBNOAccountId())
                .bot(logPlayerKillV2.isDBNOMakerBot())
                .build();
        CharacterInfo finisher = CharacterInfo.builder()
                .name(logPlayerKillV2.getFinisherName())
                .accountId(logPlayerKillV2.getFinisherAccountId())
                .bot(logPlayerKillV2.isFinisherBot())
                .build();
        CharacterInfo killer = CharacterInfo.builder()
                .name(logPlayerKillV2.getKillerName())
                .accountId(logPlayerKillV2.getKillerAccountId())
                .bot(logPlayerKillV2.isKillerBot())
                .build();

        DamageInfo dBNOInfo = DamageInfo.builder()
                .damageReason(DamageReason.of(logPlayerKillV2.getDBNODamageReason()))
                .damageTypeCategory(DamageTypeCategory.of(logPlayerKillV2.getDBNODamageTypeCategory()))
                .damageCauserName(DamageCauserName.of(logPlayerKillV2.getDBNODamageCauserName()))
                .distance(logPlayerKillV2.getDBNODistance())
                .build();
        DamageInfo finishInfo = DamageInfo.builder()
                .damageReason(DamageReason.of(logPlayerKillV2.getFinisherDamageReason()))
                .damageTypeCategory(DamageTypeCategory.of(logPlayerKillV2.getFinisherDamageTypeCategory()))
                .damageCauserName(DamageCauserName.of(logPlayerKillV2.getFinisherDamageCauserName()))
                .distance(logPlayerKillV2.getFinisherDistance())
                .build();
        DamageInfo killInfo = DamageInfo.builder()
                .damageReason(DamageReason.of(logPlayerKillV2.getKillerDamageReason()))
                .damageTypeCategory(DamageTypeCategory.of(logPlayerKillV2.getKillerDamageTypeCategory()))
                .damageCauserName(DamageCauserName.of(logPlayerKillV2.getKillerDamageCauserName()))
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
        private final String name;
        private final String accountId;
        private final Boolean bot;

        @Builder
        public CharacterInfo(String name, String accountId, Boolean bot) {
            this.name = name;
            this.accountId = accountId;
            this.bot = bot;
        }
    }

    @Getter
    public static class DamageInfo {
        private final DamageReason damageReason; // enum
        private final DamageTypeCategory damageTypeCategory; // enum
        private final DamageCauserName damageCauserName; // enum
        private final Float distance;

        @Builder
        public DamageInfo(DamageReason damageReason, DamageTypeCategory damageTypeCategory, DamageCauserName damageCauserName, Float distance) {
            this.damageReason = damageReason;
            this.damageTypeCategory = damageTypeCategory;
            this.damageCauserName = damageCauserName;
            this.distance = distance;
        }
    }
}
