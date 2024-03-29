package com.menu.pubganalyzer.matches.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.menu.pubganalyzer.matches.model.Match;
import com.menu.pubganalyzer.matches.model.Participant;
import com.menu.pubganalyzer.matches.model.Roster;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Builder
@AllArgsConstructor
public class MatchResultResponse {
    private final int rank;
    private final int rosters;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<String> member;
    private final int kills;
    private final int assists;
    private final float damageDealt;
    private final int revives;

    public static MatchResultResponse of(Match match, Roster roster, Participant participant) {
        return MatchResultResponse.builder()
                .rank(participant.getWinPlace())
                .rosters(match.getRosters().size())
                .member(new ArrayList<>(roster.extractParticipantNameWithout(participant.getName())))
                .kills(participant.getKills())
                .assists(participant.getAssists())
                .damageDealt(participant.getDamageDealt())
                .revives(participant.getRevives())
                .build();
    }
}
