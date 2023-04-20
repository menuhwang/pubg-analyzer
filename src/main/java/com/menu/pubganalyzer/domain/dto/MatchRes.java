package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Participant;
import com.menu.pubganalyzer.domain.model.Roster;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class MatchRes {
    private String id;
    private String gameMode;
    private int duration;
    private String mapName;
    private boolean isCustomMatch;
    private String matchType;
    private LocalDateTime createdAt;
    private Set<Participant> participants;
    private Set<Roster> rosters;

    private MatchRes() {
    }

    public static MatchRes of(Match match) {
        return MatchRes.builder()
                .id(match.getId())
                .gameMode(match.getGameMode().getTitle())
                .duration(match.getDuration())
                .mapName(match.getMapName().getMapNameKor())
                .isCustomMatch(match.isCustomMatch())
                .matchType(match.getMatchType().getTitle())
                .createdAt(match.getCreatedAt())
                .rosters(match.getRosters())
                .build();
    }

    public String getCreatedAt() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(this.createdAt, ZoneId.of("UTC"));
        ZonedDateTime createdAtKor = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Seoul"));
        return createdAtKor.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }
}
