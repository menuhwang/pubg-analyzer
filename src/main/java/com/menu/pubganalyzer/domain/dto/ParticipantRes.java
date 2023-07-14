package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.model.Participant;
import lombok.Getter;

@Getter
public class ParticipantRes {
    private final MatchInfoRes match;
    private final MatchResultRes stat;

    private ParticipantRes(MatchInfoRes match, MatchResultRes stat) {
        this.match = match;
        this.stat = stat;
    }

    public static ParticipantRes of(Participant participant) {
        return new ParticipantRes(
                MatchInfoRes.of(participant.getMatch()),
                MatchResultRes.of(participant.getMatch(), participant)
        );
    }
}
