package com.menu.pubganalyzer.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.menu.pubganalyzer.domain.model.matches.Match;
import com.menu.pubganalyzer.domain.model.matches.Participant;
import com.menu.pubganalyzer.domain.model.matches.Roster;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MatchResultRes {
    private final int rank;
    private final int rosters;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<String> member;
    private final int kills;
    private final int assists;
    private final float damageDealt;
    private final int revives;

    public static MatchResultRes of(Match match, Roster roster, Participant participant) {
        return MatchResultRes.builder()
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
