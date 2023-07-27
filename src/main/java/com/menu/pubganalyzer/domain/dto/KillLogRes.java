package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.model.telemetries.LogPlayerKillV2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class KillLogRes {
    private final LocalDateTime timestamp;
    private final CharacterRes victim;
    private final CharacterRes dBNOMaker;
    private final DamageInfoRes dBNOInfo;
    private final CharacterRes finisher;
    private final DamageInfoRes finishInfo;
    private final CharacterRes killer;
    private final DamageInfoRes killInfo;
    private final boolean suicide;

    public static KillLogRes of(LogPlayerKillV2 logPlayerKillV2) {
        CharacterRes victim = CharacterRes.from(logPlayerKillV2.getVictim());
        CharacterRes dBNOMaker = CharacterRes.from(logPlayerKillV2.getDBNOMaker());
        CharacterRes finisher = CharacterRes.from(logPlayerKillV2.getFinisher());
        CharacterRes killer = CharacterRes.from(logPlayerKillV2.getKiller());

        DamageInfoRes dBNOInfo = DamageInfoRes.from(logPlayerKillV2.getDBNODamageInfo());
        DamageInfoRes finishInfo = DamageInfoRes.from(logPlayerKillV2.getFinisherDamageInfo());
        DamageInfoRes killInfo = DamageInfoRes.from(logPlayerKillV2.getKillerDamageInfo());

        return KillLogRes.builder()
                .timestamp(logPlayerKillV2.getTimestamp())
                .victim(victim)
                .dBNOMaker(dBNOMaker)
                .dBNOInfo(dBNOInfo)
                .finisher(finisher)
                .finishInfo(finishInfo)
                .killer(killer)
                .killInfo(killInfo)
                .suicide(logPlayerKillV2.isSuicide())
                .build();
    }
}
