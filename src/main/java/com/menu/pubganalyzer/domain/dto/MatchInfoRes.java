package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.model.enums.match.GameMode;
import com.menu.pubganalyzer.domain.model.enums.match.MapName;
import com.menu.pubganalyzer.domain.model.enums.match.MatchType;
import com.menu.pubganalyzer.domain.model.matches.Match;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class MatchInfoRes {
    private final String id;
    private final MatchType matchType;
    private final MapName mapName;
    private final GameMode gameMode;
    private final LocalDateTime createdAt;
    private final Integer duration;

    @Builder
    public MatchInfoRes(String id, MatchType matchType, MapName mapName, GameMode gameMode, LocalDateTime createdAt, Integer duration) {
        this.id = id;
        this.matchType = matchType;
        this.mapName = mapName;
        this.gameMode = gameMode;
        this.createdAt = createdAt;
        this.duration = duration;
    }

    public static MatchInfoRes from(Match match) {
        return MatchInfoRes.builder()
                .id(match.getId())
                .matchType(MatchType.of(match.getMatchType()))
                .mapName(MapName.of(match.getMapName()))
                .gameMode(GameMode.of(match.getGameMode()))
                .createdAt(match.getCreatedAt())
                .duration(match.getDuration())
                .build();
    }
}
