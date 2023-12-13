package com.menu.pubganalyzer.matches.dto.response;

import com.menu.pubganalyzer.matches.model.Match;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MatchStatsResponse {
    private final MatchResponse match;
    private final RosterResponse roster;
    private final ParticipantResponse stats;

    public static MatchStatsResponse of(Match match, String playerName) {
        return new MatchStatsResponse(
                MatchResponse.from(match),
                RosterResponse.from(match.getRosterByName(playerName)),
                ParticipantResponse.from(match.getParticipantByName(playerName))
        );
    }
}
