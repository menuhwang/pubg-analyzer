package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.model.matches.Match;
import com.menu.pubganalyzer.domain.repository.TelemetryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.menu.pubganalyzer.support.fixture.MatchFixture.MATCH;
import static com.menu.pubganalyzer.support.fixture.MatchFixture.MATCH_ID;
import static com.menu.pubganalyzer.support.fixture.PlayerFixture.PLAYER_NAME;
import static com.menu.pubganalyzer.support.fixture.TelemetryFixture.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class TelemetryServiceTest {
    private final PubgService pubgService = Mockito.mock(PubgService.class);
    private final TelemetryRepository telemetryRepository = Mockito.mock(TelemetryRepository.class);
    private final TelemetryService telemetryService = new TelemetryService(pubgService, telemetryRepository);

    @Test
    void findKillLogsWithPubgAPI() {
        given(telemetryRepository.existsByMatchId(MATCH_ID))
                .willReturn(false);

        given(pubgService.fetchTelemetry(any(Match.class)))
                .willReturn(TELEMETRIES);

        given(telemetryRepository.findLogPlayerKillByMatchIdAndPlayerName(MATCH_ID, PLAYER_NAME))
                .willReturn(TELEMETRY_LOG_PLAYER_KILLS);

        assertDoesNotThrow(() -> telemetryService.findKillLogs(MATCH, PLAYER_NAME));

        verify(telemetryRepository).existsByMatchId(MATCH_ID);
        verify(pubgService).fetchTelemetry(any(Match.class));
        verify(telemetryRepository).saveAll(anyCollection());
        verify(telemetryRepository).findLogPlayerKillByMatchIdAndPlayerName(MATCH_ID, PLAYER_NAME);
    }

    @Test
    void findKillLogsOnlyRepository() {
        given(telemetryRepository.existsByMatchId(MATCH_ID))
                .willReturn(true);

        given(telemetryRepository.findLogPlayerKillByMatchIdAndPlayerName(MATCH_ID, PLAYER_NAME))
                .willReturn(TELEMETRY_LOG_PLAYER_KILLS);

        assertDoesNotThrow(() -> telemetryService.findKillLogs(MATCH, PLAYER_NAME));

        verify(telemetryRepository).existsByMatchId(MATCH_ID);
        verify(pubgService, never()).fetchTelemetry(any(Match.class));
        verify(telemetryRepository, never()).saveAll(anyCollection());
        verify(telemetryRepository).findLogPlayerKillByMatchIdAndPlayerName(MATCH_ID, PLAYER_NAME);
    }

    @Test
    void findDamageLogsByVictimsAndMemberWithPubgAPI() {
        given(telemetryRepository.existsByMatchId(MATCH_ID))
                .willReturn(false);

        given(pubgService.fetchTelemetry(any(Match.class)))
                .willReturn(TELEMETRIES);

        given(telemetryRepository.findLogPlayerTakeDamageByVictimsAndAttacker(eq(MATCH_ID), anyCollection(), anyCollection()))
                .willReturn(TELEMETRY_LOG_PLAYER_TAKE_DAMAGES);

        assertDoesNotThrow(() -> telemetryService.findDamageLogs(MATCH, List.of("victim"), List.of("member")));

        verify(telemetryRepository).existsByMatchId(MATCH_ID);
        verify(pubgService).fetchTelemetry(any(Match.class));
        verify(telemetryRepository).saveAll(anyCollection());
        verify(telemetryRepository).findLogPlayerTakeDamageByVictimsAndAttacker(eq(MATCH_ID), anyCollection(), anyCollection());
    }

    @Test
    void findDamageLogsByVictimsAndMemberOnlyRepository() {
        given(telemetryRepository.existsByMatchId(MATCH_ID))
                .willReturn(true);

        given(telemetryRepository.findLogPlayerTakeDamageByVictimsAndAttacker(eq(MATCH_ID), anyCollection(), anyCollection()))
                .willReturn(TELEMETRY_LOG_PLAYER_TAKE_DAMAGES);

        assertDoesNotThrow(() -> telemetryService.findDamageLogs(MATCH, List.of("victim"), List.of("member")));

        verify(telemetryRepository).existsByMatchId(MATCH_ID);
        verify(pubgService, never()).fetchTelemetry(any(Match.class));
        verify(telemetryRepository, never()).saveAll(anyCollection());
        verify(telemetryRepository).findLogPlayerTakeDamageByVictimsAndAttacker(eq(MATCH_ID), anyCollection(), anyCollection());
    }

    @Test
    void findDamageLogsByAttackerWithPubgAPI() {
        given(telemetryRepository.existsByMatchId(MATCH_ID))
                .willReturn(false);

        given(pubgService.fetchTelemetry(any(Match.class)))
                .willReturn(TELEMETRIES);

        given(telemetryRepository.findLogPlayerTakeDamageByAttacker(MATCH_ID, PLAYER_NAME))
                .willReturn(TELEMETRY_LOG_PLAYER_TAKE_DAMAGES);

        assertDoesNotThrow(() -> telemetryService.findDamageLogs(MATCH, PLAYER_NAME));

        verify(telemetryRepository).existsByMatchId(MATCH_ID);
        verify(pubgService).fetchTelemetry(any(Match.class));
        verify(telemetryRepository).saveAll(anyCollection());
        verify(telemetryRepository).findLogPlayerTakeDamageByAttacker(MATCH_ID, PLAYER_NAME);
    }

    @Test
    void findDamageLogsByAttackerOnlyRepository() {
        given(telemetryRepository.existsByMatchId(MATCH_ID))
                .willReturn(true);

        given(telemetryRepository.findLogPlayerTakeDamageByAttacker(MATCH_ID, PLAYER_NAME))
                .willReturn(TELEMETRY_LOG_PLAYER_TAKE_DAMAGES);

        assertDoesNotThrow(() -> telemetryService.findDamageLogs(MATCH, PLAYER_NAME));

        verify(telemetryRepository).existsByMatchId(MATCH_ID);
        verify(pubgService, never()).fetchTelemetry(any(Match.class));
        verify(telemetryRepository, never()).saveAll(anyCollection());
        verify(telemetryRepository).findLogPlayerTakeDamageByAttacker(MATCH_ID, PLAYER_NAME);
    }
}