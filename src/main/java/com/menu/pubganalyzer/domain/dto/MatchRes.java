package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.model.matches.Match;
import com.menu.pubganalyzer.domain.model.enums.match.GameMode;
import com.menu.pubganalyzer.domain.model.enums.match.MapName;
import com.menu.pubganalyzer.domain.model.enums.match.MatchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class MatchRes {
    private String id;
    private GameMode gameMode;
    private int duration;
    private MapName mapName;
    private boolean isCustomMatch;
    private MatchType matchType;
    private int rosters;
    private LocalDateTime createdAt;

    private MatchRes() {
    }

    public static MatchRes from(Match match) {
        return MatchRes.builder()
                .id(match.getId())
                .gameMode(GameMode.of(match.getGameMode()))
                .duration(match.getDuration())
                .mapName(MapName.of(match.getMapName()))
                .isCustomMatch(match.isCustomMatch())
                .matchType(MatchType.of(match.getMatchType()))
                .rosters(match.getRosters().size())
                .createdAt(match.getCreatedAt())
                .build();
    }
}
