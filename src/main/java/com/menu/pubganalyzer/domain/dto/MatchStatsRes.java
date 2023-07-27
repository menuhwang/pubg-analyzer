package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.model.matches.Match;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MatchStatsRes {
    private final MatchRes match;
    private final RosterRes roster;
    private final ParticipantRes stats;

    public static MatchStatsRes of(Match match, String playerName) {
        return new MatchStatsRes(
                MatchRes.from(match),
                RosterRes.from(match.getRosterByName(playerName)),
                ParticipantRes.from(match.getParticipantByName(playerName))
        );
    }
}
