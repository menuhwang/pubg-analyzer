package com.menu.pubganalyzer.telemetries.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class CharacterResponse {
    private final String name;
    private final Integer teamId;
    private final float health;
    private final Integer ranking;
    private final String accountId;
    private final boolean bot;

    public static CharacterResponse from(com.menu.pubganalyzer.util.pubg.response.telemetry.objects.CharacterResponse character) {
        if (Objects.isNull(character)) return null;
        return CharacterResponse.builder()
                .name(character.getName())
                .teamId(character.getTeamId())
                .health(character.getHealth())
                .ranking(character.getRanking())
                .accountId(character.getAccountId())
                .bot(character.isBot())
                .build();
    }
}
