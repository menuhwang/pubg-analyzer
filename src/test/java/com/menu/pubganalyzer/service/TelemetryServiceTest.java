package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.dto.ContributeDamageChartRes;
import com.menu.pubganalyzer.domain.dto.PhaseDamageChartRes;
import com.menu.pubganalyzer.domain.repository.MatchRepository;
import com.menu.pubganalyzer.domain.repository.TelemetryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

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
    private final MatchRepository matchRepository = Mockito.mock(MatchRepository.class);
    private final TelemetryRepository telemetryRepository = Mockito.mock(TelemetryRepository.class);
    private final TelemetryService telemetryService = new TelemetryService(pubgService, matchRepository ,telemetryRepository);
    private final Logger logger = LoggerFactory.getLogger(getClass());

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

    @Test
    void findDamagesOfKillWithFetchAPI() {
        given(matchRepository.findById(MATCH_ID))
                .willReturn(Optional.of(MATCH));

        given(telemetryRepository.existsByMatchId(MATCH_ID))
                .willReturn(false);

        given(telemetryRepository.findLogPlayerKillByMatchIdAndPlayerName(MATCH_ID, PLAYER_NAME))
                .willReturn(OFFICIAL_TELEMETRIES_LOG_PLAYER_KILLS);

        given(telemetryRepository.findLogPlayerTakeDamageByVictimsAndAttacker(eq(MATCH_ID), any(), any()))
                .willReturn(OFFICIAL_TELEMETRIES_LOG_PLAYER_TAKE_DAMAGES);

        assertDoesNotThrow(() -> telemetryService.findDamagesOfKill(MATCH_ID, PLAYER_NAME));

        verify(pubgService).fetchTelemetry(any());
        verify(telemetryRepository).saveAll(any(), any());
    }

    @Test
    void findDamagesOfKill() {
        given(matchRepository.findById(MATCH_ID))
                .willReturn(Optional.of(MATCH));

        given(telemetryRepository.existsByMatchId(MATCH_ID))
                .willReturn(true);

        given(telemetryRepository.findLogPlayerKillByMatchIdAndPlayerName(MATCH_ID, PLAYER_NAME))
                .willReturn(OFFICIAL_TELEMETRIES_LOG_PLAYER_KILLS);

        given(telemetryRepository.findLogPlayerTakeDamageByVictimsAndAttacker(eq(MATCH_ID), any(), any()))
                .willReturn(OFFICIAL_TELEMETRIES_LOG_PLAYER_TAKE_DAMAGES);

        assertDoesNotThrow(() -> telemetryService.findDamagesOfKill(MATCH_ID, PLAYER_NAME));

        verify(pubgService, never()).fetchTelemetry(any());
        verify(telemetryRepository, never()).saveAll(any(), any());
    }

    @Test
    void findDamageLogByPlayer() {
        given(telemetryRepository.findLogPlayerTakeDamageByAttacker(MATCH_ID, PLAYER_NAME))
                .willReturn(OFFICIAL_TELEMETRIES_LOG_PLAYER_TAKE_DAMAGES);

        assertDoesNotThrow(() -> telemetryService.findDamageLogByPlayer(MATCH_ID, PLAYER_NAME));
    }

    @Test
    void findKillLogs() {
        given(telemetryRepository.findLogPlayerKillByMatchIdAndPlayerName(MATCH_ID, PLAYER_NAME))
                .willReturn(OFFICIAL_TELEMETRIES_LOG_PLAYER_KILLS);

        assertDoesNotThrow(() -> telemetryService.findKillLogs(MATCH_ID, PLAYER_NAME));
    }

    @Test
    void getPhaseDamageChart() {
        given(telemetryRepository.findLogPlayerTakeDamageByAttacker(MATCH_ID, PLAYER_NAME))
                .willReturn(OFFICIAL_TELEMETRIES_LOG_PLAYER_TAKE_DAMAGES);

        PhaseDamageChartRes result = assertDoesNotThrow(() -> telemetryService.getPhaseDamageChart(MATCH_ID, PLAYER_NAME));

        logger.info("phase damage chart: {}", result);
    }

    @Test
    void getContributeDamageChart() {
        given(matchRepository.findById(MATCH_ID))
                .willReturn(Optional.of(MATCH));

        given(telemetryRepository.findLogPlayerKillByMatchIdAndPlayerName(MATCH_ID, PLAYER_NAME))
                .willReturn(OFFICIAL_TELEMETRIES_LOG_PLAYER_KILLS);

        given(telemetryRepository.findLogPlayerTakeDamageByVictimsAndAttacker(eq(MATCH_ID), anyCollection(), anyCollection()))
                .willReturn(OFFICIAL_TELEMETRIES_LOG_PLAYER_TAKE_DAMAGES);

        ContributeDamageChartRes result = assertDoesNotThrow(() -> telemetryService.getContributeDamageChart(MATCH_ID, PLAYER_NAME));
        logger.info("contribute damage chart: {}", result);
    }
}