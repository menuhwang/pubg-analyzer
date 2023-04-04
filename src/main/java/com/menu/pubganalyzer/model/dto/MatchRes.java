package com.menu.pubganalyzer.model.dto;

import com.menu.pubganalyzer.model.Match;
import com.menu.pubganalyzer.model.Participant;
import com.menu.pubganalyzer.model.Roster;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                .participants(match.getParticipants())
                .rosters(match.getRosters())
                .build();
    }

    public static List<MatchRes> of(Collection<Match> matches) {
        return matches.stream()
                .map(MatchRes::of)
                .sorted(Comparator.comparing(MatchRes::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    public Participant getParticipantById(String playerId) {
        return participants.stream()
                .filter(p -> p.getPlayerId().equals(playerId))
                .findFirst()
                .orElse(null);
    }
}
