package com.menu.pubganalyzer.support.fixture;

import com.menu.pubganalyzer.matches.model.Match;
import com.menu.pubganalyzer.matches.model.Participant;
import com.menu.pubganalyzer.matches.model.Roster;
import com.menu.pubganalyzer.support.fixture.pubgapi.MatchResponseFixture;
import com.menu.pubganalyzer.support.fixture.pubgapi.PlayerResponseFixture;
import org.springframework.data.domain.*;

import java.util.List;

public class MatchFixture {

    private static final String PLAYER_NAME = PlayerResponseFixture.PLAYER_NAME;
    public static final String MATCH_SHARD = MatchResponseFixture.MATCH_SHARD;
    public static final String MATCH_ID = MatchResponseFixture.MATCH_ID;
    private static final long TOTAL_SIZE = 1L;
    public static final Pageable PAGEABLE = PageRequest.of(0, 20, Sort.by(Sort.Order.desc("createdAt")));

    public static final Participant PARTICIPANT;

    public static final Roster ROSTER;

    public static final Match MATCH;

    public static final Page<Match> MATCH_PAGE;

    static {
        MATCH = Match.of(MatchResponseFixture.MATCH_RESPONSE);

        ROSTER = MATCH.getRosterByName(PLAYER_NAME);
        PARTICIPANT = ROSTER.getParticipantByName(PLAYER_NAME);

        MATCH_PAGE = new PageImpl<>(List.of(MATCH), PAGEABLE, TOTAL_SIZE);
    }
}
