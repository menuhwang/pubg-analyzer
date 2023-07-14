package com.menu.pubganalyzer.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Participant;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MatchResultRes {
    private final int rank;
    private final int rosters;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<String> member;
    private final int kills;
    private final int assists;
    private final float damageDealt;
    private final int revives;

    @Builder
    public MatchResultRes(
            int rank,
            int rosters,
            List<String> member,
            int kills,
            int assists,
            float damageDealt,
            int revives) {
        this.rank = rank;
        this.rosters = rosters;
        this.member = member;
        this.kills = kills;
        this.assists = assists;
        this.damageDealt = damageDealt;
        this.revives = revives;
    }

    public static MatchResultRes of(Match match, Participant participant) {
        return MatchResultRes.builder()
                .rank(participant.getWinPlace())
                .rosters(match.getRosters().size())
                .member(participant.getMember())
                .kills(participant.getKills())
                .assists(participant.getAssists())
                .damageDealt(participant.getDamageDealt())
                .revives(participant.getRevives())
                .build();
    }
}
