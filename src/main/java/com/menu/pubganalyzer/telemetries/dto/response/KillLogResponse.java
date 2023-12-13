package com.menu.pubganalyzer.telemetries.dto.response;

import com.menu.pubganalyzer.util.pubg.response.telemetry.events.LogPlayerKillV2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class KillLogResponse {
    private final LocalDateTime timestamp;
    private final CharacterResponse victim;
    private final CharacterResponse dBNOMaker;
    private final DamageInfoResponse dBNOInfo;
    private final CharacterResponse finisher;
    private final DamageInfoResponse finishInfo;
    private final CharacterResponse killer;
    private final DamageInfoResponse killInfo;
    private final boolean suicide;

    public static KillLogResponse of(LogPlayerKillV2 logPlayerKillV2) {
        CharacterResponse victim = CharacterResponse.from(logPlayerKillV2.getVictim());
        CharacterResponse dBNOMaker = CharacterResponse.from(logPlayerKillV2.getDBNOMaker());
        CharacterResponse finisher = CharacterResponse.from(logPlayerKillV2.getFinisher());
        CharacterResponse killer = CharacterResponse.from(logPlayerKillV2.getKiller());

        DamageInfoResponse dBNOInfo = DamageInfoResponse.from(logPlayerKillV2.getDBNODamageInfo());
        DamageInfoResponse finishInfo = DamageInfoResponse.from(logPlayerKillV2.getFinishDamageInfo());
        DamageInfoResponse killInfo = DamageInfoResponse.from(logPlayerKillV2.getKillerDamageInfo());

        return KillLogResponse.builder()
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
