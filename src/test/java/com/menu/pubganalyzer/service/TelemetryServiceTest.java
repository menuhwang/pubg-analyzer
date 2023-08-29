package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.repository.TelemetryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.menu.pubganalyzer.support.fixture.MatchFixture.MATCH;
import static com.menu.pubganalyzer.support.fixture.MatchFixture.MATCH_ID;
import static com.menu.pubganalyzer.support.fixture.PlayerFixture.PLAYER_NAME;
import static com.menu.pubganalyzer.support.fixture.TelemetryFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class TelemetryServiceTest {
    private final PubgService pubgService = Mockito.mock(PubgService.class);
    private final TelemetryRepository telemetryRepository = Mockito.mock(TelemetryRepository.class);
    private final TelemetryService telemetryService = new TelemetryService(pubgService, telemetryRepository);

    @Test
    void findKillLogs() {
        given(telemetryRepository.findLogPlayerKillByMatchIdAndPlayerName(MATCH_ID, PLAYER_NAME))
                .willReturn(OFFICIAL_TELEMETRIES_LOG_PLAYER_KILLS);

        assertDoesNotThrow(() -> telemetryService.findKillLogs(MATCH, PLAYER_NAME));

        verify(telemetryRepository, never()).saveAll(eq(MATCH_ID), anyCollection());
        verify(telemetryRepository).findLogPlayerKillByMatchIdAndPlayerName(MATCH_ID, PLAYER_NAME);
    }

    @Test
    void findDamageLogsByVictimsAndMember() {
        given(telemetryRepository.findLogPlayerTakeDamageByVictimsAndAttacker(eq(MATCH_ID), anyCollection(), anyCollection()))
                .willReturn(OFFICIAL_TELEMETRIES_LOG_PLAYER_TAKE_DAMAGES);

        assertDoesNotThrow(() -> telemetryService.findDamageLogs(MATCH, List.of("victim"), List.of("member")));

        verify(telemetryRepository, never()).saveAll(eq(MATCH_ID), anyCollection());
        verify(telemetryRepository).findLogPlayerTakeDamageByVictimsAndAttacker(eq(MATCH_ID), anyCollection(), anyCollection());
    }

    @Test
    void findDamageLogsByAttacker() {
        given(telemetryRepository.findLogPlayerTakeDamageByAttacker(MATCH_ID, PLAYER_NAME))
                .willReturn(OFFICIAL_TELEMETRIES_LOG_PLAYER_TAKE_DAMAGES);

        assertDoesNotThrow(() -> telemetryService.findDamageLogs(MATCH, PLAYER_NAME));

        verify(telemetryRepository, never()).saveAll(eq(MATCH_ID), anyCollection());
        verify(telemetryRepository).findLogPlayerTakeDamageByAttacker(MATCH_ID, PLAYER_NAME);
    }

    @Test
    void existsTelemetry() {
        given(telemetryRepository.existsByMatchId(anyString()))
                .willReturn(true);

        boolean result = assertDoesNotThrow(() -> telemetryService.existsTelemetry(MATCH));

        assertTrue(result);

        verify(telemetryRepository).existsByMatchId(anyString());
    }

    @Test
    void notExistsTelemetry() {
        given(telemetryRepository.existsByMatchId(anyString()))
                .willReturn(false);

        boolean result = assertDoesNotThrow(() -> telemetryService.existsTelemetry(MATCH));

        assertFalse(result);

        verify(telemetryRepository).existsByMatchId(anyString());
    }

    @Test
    void fetchTelemetry() {
        assertDoesNotThrow(() -> telemetryService.fetchTelemetry(MATCH));

        verify(pubgService).fetchTelemetry(MATCH);
        verify(telemetryRepository).saveAll(eq(MATCH_ID), anyCollection());
    }
}