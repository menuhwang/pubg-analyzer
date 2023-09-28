package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.fetch.service.FetchAPIService;
import com.menu.pubganalyzer.fetch.service.PubgService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.menu.pubganalyzer.support.fixture.PlayerFixture.PLAYER_NAME;
import static com.menu.pubganalyzer.support.fixture.PlayerFixture.PLAYER_SHARD;
import static com.menu.pubganalyzer.support.fixture.pubgapi.MatchResponseFixture.MATCH_ID;
import static com.menu.pubganalyzer.support.fixture.pubgapi.MatchResponseFixture.MATCH_RESPONSE;
import static com.menu.pubganalyzer.support.fixture.pubgapi.PlayerResponseFixture.PLAYERS_RESPONSE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class FetchAPIServiceTest {
    private final PubgService pubgService = Mockito.mock(PubgService.class);
    private final FetchAPIService fetchAPIService = new FetchAPIService(pubgService);

    @Test
    void player() {
        given(pubgService.fetchPlayer(PLAYER_SHARD, PLAYER_NAME))
                .willReturn(PLAYERS_RESPONSE);

        assertDoesNotThrow(() -> fetchAPIService.player(PLAYER_SHARD, PLAYER_NAME));

        verify(pubgService).fetchPlayer(PLAYER_SHARD, PLAYER_NAME);
    }

    @Test
    void match() {
        given(pubgService.fetchMatch(PLAYER_SHARD, MATCH_ID))
                .willReturn(MATCH_RESPONSE);

        assertDoesNotThrow(() -> fetchAPIService.match(PLAYER_SHARD, MATCH_ID));

        verify(pubgService).fetchMatch(PLAYER_SHARD, MATCH_ID);
    }
}