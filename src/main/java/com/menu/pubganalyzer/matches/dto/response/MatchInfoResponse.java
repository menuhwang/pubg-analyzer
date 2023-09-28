package com.menu.pubganalyzer.matches.dto.response;

import com.menu.pubganalyzer.matches.dto.response.enums.GameMode;
import com.menu.pubganalyzer.matches.dto.response.enums.MapName;
import com.menu.pubganalyzer.matches.dto.response.enums.MatchType;
import com.menu.pubganalyzer.matches.model.Match;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class MatchInfoResponse {
    private final String id;
    private final MatchType matchType;
    private final MapName mapName;
    private final GameMode gameMode;
    private final LocalDateTime createdAt;
    private final Integer duration;

    @Builder
    public MatchInfoResponse(String id, MatchType matchType, MapName mapName, GameMode gameMode, LocalDateTime createdAt, Integer duration) {
        this.id = id;
        this.matchType = matchType;
        this.mapName = mapName;
        this.gameMode = gameMode;
        this.createdAt = createdAt;
        this.duration = duration;
    }

    public static MatchInfoResponse from(Match match) {
        return MatchInfoResponse.builder()
                .id(match.getId())
                .matchType(MatchType.of(match.getMatchType()))
                .mapName(MapName.of(match.getMapName()))
                .gameMode(GameMode.of(match.getGameMode()))
                .createdAt(match.getCreatedAt())
                .duration(match.getDuration())
                .build();
    }
}
