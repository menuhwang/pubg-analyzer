package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.model.telemetries.Character;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class CharacterRes {
    private final String name;
    private final Integer teamId;
    private final float health;
    private final Integer ranking;
    private final String accountId;
    private final boolean bot;

    public static CharacterRes from(Character character) {
        if (Objects.isNull(character)) return null;
        return CharacterRes.builder()
                .name(character.getName())
                .teamId(character.getTeamId())
                .health(character.getHealth())
                .ranking(character.getRanking())
                .accountId(character.getAccountId())
                .bot(character.isBot())
                .build();
    }
}
