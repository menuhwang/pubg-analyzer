package com.menu.pubganalyzer.support.fixture;

import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Participant;
import com.menu.pubganalyzer.domain.model.Roster;

import java.util.Set;

public class MatchFixture {
    public static final String PLAYER_NAME = "test-participant";
    public static final String MATCH_ID = "test-match";

    public static final Participant PARTICIPANT = Participant.builder()
            .name(PLAYER_NAME)
            .build();

    public static final Roster ROSTER = Roster.builder().build();

    public static final Match MATCH = Match.builder()
            .id(MATCH_ID)
            .build();

    static {
        PARTICIPANT.setMatch(MATCH);
        ROSTER.addParticipant(PARTICIPANT);
        MATCH.setRosters(Set.of(ROSTER));
    }
}
