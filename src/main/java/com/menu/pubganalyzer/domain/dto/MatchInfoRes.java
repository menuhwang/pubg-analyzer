package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.enums.match.GameMode;
import com.menu.pubganalyzer.domain.model.enums.match.MapName;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MatchInfoRes {
    private final String id;
    private final MapName map;
    private final GameMode mode;
    private final LocalDateTime createdAt;
    private final Integer duration;

    @Builder
    public MatchInfoRes(String id, MapName map, GameMode mode, LocalDateTime createdAt, Integer duration) {
        this.id = id;
        this.map = map;
        this.mode = mode;
        this.createdAt = createdAt;
        this.duration = duration;
    }

    public static MatchInfoRes of(Match match) {
        return MatchInfoRes.builder()
                .id(match.getId())
                .map(MapName.of(match.getMapName()))
                .mode(GameMode.of(match.getGameMode()))
                .createdAt(match.getCreatedAt())
                .duration(match.getDuration())
                .build();
    }
}
