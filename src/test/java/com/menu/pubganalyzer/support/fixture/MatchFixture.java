package com.menu.pubganalyzer.support.fixture;

import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Participant;
import com.menu.pubganalyzer.domain.model.Roster;
import com.menu.pubganalyzer.support.fixture.pubgapi.MatchResponseFixture;
import com.menu.pubganalyzer.support.fixture.pubgapi.PlayerResponseFixture;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Set;

public class MatchFixture {

    private static final String PLAYER_NAME = PlayerResponseFixture.PLAYER_NAME;
    public static final String MATCH_SHARD = MatchResponseFixture.MATCH_SHARD;
    public static final String MATCH_ID = MatchResponseFixture.MATCH_ID;
    private static final long TOTAL_SIZE = 1L;
    public static final Pageable PAGEABLE = PageRequest.of(0, 20, Sort.by(Sort.Order.desc("createdAt")));

    public static final Participant PARTICIPANT = Participant.builder()
            .name(PLAYER_NAME)
            .build();

    public static final Roster ROSTER = Roster.builder().build();

    public static final Match MATCH = Match.of(MatchResponseFixture.MATCH_RESPONSE);

    public static final Page<Match> MATCH_PAGE = new PageImpl<>(List.of(MATCH), PAGEABLE, TOTAL_SIZE);

    static {
        PARTICIPANT.setMatch(MATCH);
        ROSTER.addParticipant(PARTICIPANT);
        MATCH.setRosters(Set.of(ROSTER));
    }
}
