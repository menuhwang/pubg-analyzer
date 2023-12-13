package com.menu.pubganalyzer.fetch.service;

import com.menu.pubganalyzer.config.tasks.CustomThreadPoolConfig;
import com.menu.pubganalyzer.matches.model.Match;
import com.menu.pubganalyzer.util.pubg.PubgClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.task.TaskExecutor;

import java.util.Collections;
import java.util.List;

import static com.menu.pubganalyzer.support.fixture.MatchFixture.*;
import static com.menu.pubganalyzer.support.fixture.PlayerFixture.PLAYER_NAME;
import static com.menu.pubganalyzer.support.fixture.PlayerFixture.PLAYER_SHARD;
import static com.menu.pubganalyzer.support.fixture.pubgapi.MatchResponseFixture.MATCH_ASSET_URL;
import static com.menu.pubganalyzer.support.fixture.pubgapi.MatchResponseFixture.MATCH_RESPONSE;
import static com.menu.pubganalyzer.support.fixture.pubgapi.PlayerResponseFixture.PLAYERS_RESPONSE;
import static com.menu.pubganalyzer.support.fixture.pubgapi.TelemetryResponseFixture.OFFICIAL_TELEMETRY_RESPONSE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class PubgServiceTest {
    private final PubgClient pubgClient = Mockito.mock(PubgClient.class);
    private final TaskExecutor pubgApiExecutor = new CustomThreadPoolConfig().pubgApiExecutor();
    private final PubgService pubgService = new PubgService(pubgClient, pubgApiExecutor);

    @Test
    void fetchPlayerReturnRaw() {
        given(pubgClient.player(eq(PLAYER_SHARD), eq(PLAYER_NAME)))
                .willReturn(PLAYERS_RESPONSE);

        assertDoesNotThrow(() -> pubgService.fetchPlayer(PLAYER_SHARD, PLAYER_NAME));

        verify(pubgClient).player(eq(PLAYER_SHARD), eq(PLAYER_NAME));
    }

    @Test
    void fetchMatch() {
        given(pubgClient.match(eq(MATCH_SHARD), eq(MATCH_ID)))
                .willReturn(MATCH_RESPONSE);

        assertDoesNotThrow(() -> pubgService.fetchMatch(MATCH_SHARD, MATCH_ID));

        verify(pubgClient).match(eq(MATCH_SHARD), eq(MATCH_ID));
    }

    @Test
    void fetchPlayerReturnDomain() {
        given(pubgClient.player(eq(PLAYER_SHARD.toLowerCase()), eq(PLAYER_NAME)))
                .willReturn(PLAYERS_RESPONSE);

        assertDoesNotThrow(() -> pubgService.fetchPlayer(PLAYER_NAME));

        verify(pubgClient).player(eq(PLAYER_SHARD.toLowerCase()), eq(PLAYER_NAME));
    }

    @Test
    void fetchMatches() {
        List<String> matchIds = List.of(MATCH_ID);
        given(pubgClient.match(anyString(), eq(MATCH_ID)))
                .willReturn(MATCH_RESPONSE);

        List<Match> result = assertDoesNotThrow(() -> pubgService.fetchMatches(matchIds));

        assertFalse(result.isEmpty());

        verify(pubgClient).match(anyString(), eq(MATCH_ID));
    }

    @Test
    void fetchMatchesWhenEmptyMatchIds() {
        List<String> emptyMatchIds = Collections.emptyList();

        List<Match> result = assertDoesNotThrow(() -> pubgService.fetchMatches(emptyMatchIds));

        assertTrue(result.isEmpty());

        verify(pubgClient, never()).match(anyString(), anyString());
    }

    @Test
    void fetchTelemetry() {
        given(pubgClient.telemetry(MATCH_ASSET_URL))
                .willReturn(OFFICIAL_TELEMETRY_RESPONSE);

        assertDoesNotThrow(() -> pubgService.fetchTelemetry(MATCH));

        verify(pubgClient).telemetry(MATCH_ASSET_URL);
    }
}