package com.menu.pubganalyzer.telemetries.service;

import com.menu.pubganalyzer.telemetries.dto.response.ContributeDamageChartResponse;
import com.menu.pubganalyzer.telemetries.dto.response.PhaseDamageChartResponse;
import com.menu.pubganalyzer.fetch.service.PubgService;
import com.menu.pubganalyzer.matches.repository.MatchRepository;
import com.menu.pubganalyzer.telemetries.repository.TelemetryRepository;
import com.menu.pubganalyzer.telemetries.service.TelemetryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.menu.pubganalyzer.support.fixture.MatchFixture.MATCH;
import static com.menu.pubganalyzer.support.fixture.MatchFixture.MATCH_ID;
import static com.menu.pubganalyzer.support.fixture.PlayerFixture.PLAYER_NAME;
import static com.menu.pubganalyzer.support.fixture.TelemetryFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class TelemetryServiceTest {
    private final PubgService pubgService = Mockito.mock(PubgService.class);
    private final MatchRepository matchRepository = Mockito.mock(MatchRepository.class);
    private final TelemetryRepository telemetryRepository = Mockito.mock(TelemetryRepository.class);
    private final TelemetryService telemetryService = new TelemetryService(pubgService, matchRepository ,telemetryRepository);
    private final Logger logger = LoggerFactory.getLogger(getClass());

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
        given(matchRepository.findById(MATCH_ID))
                .willReturn(Optional.of(MATCH));

        given(telemetryRepository.existsByMatchId(MATCH_ID))
                .willReturn(true);

        given(telemetryRepository.findLogPlayerTakeDamageByAttacker(MATCH_ID, PLAYER_NAME))
                .willReturn(OFFICIAL_TELEMETRIES_LOG_PLAYER_TAKE_DAMAGES);

        assertDoesNotThrow(() -> telemetryService.findDamageLogByPlayer(MATCH_ID, PLAYER_NAME));
    }

    @Test
    void findKillLogs() {
        given(matchRepository.findById(MATCH_ID))
                .willReturn(Optional.of(MATCH));

        given(telemetryRepository.existsByMatchId(MATCH_ID))
                .willReturn(true);

        given(telemetryRepository.findLogPlayerKillByMatchIdAndPlayerName(MATCH_ID, PLAYER_NAME))
                .willReturn(OFFICIAL_TELEMETRIES_LOG_PLAYER_KILLS);

        assertDoesNotThrow(() -> telemetryService.findKillLogs(MATCH_ID, PLAYER_NAME));
    }

    @Test
    void getPhaseDamageChart() {
        given(matchRepository.findById(MATCH_ID))
                .willReturn(Optional.of(MATCH));

        given(telemetryRepository.existsByMatchId(MATCH_ID))
                .willReturn(true);

        given(telemetryRepository.findLogPlayerTakeDamageByAttacker(MATCH_ID, PLAYER_NAME))
                .willReturn(OFFICIAL_TELEMETRIES_LOG_PLAYER_TAKE_DAMAGES);

        PhaseDamageChartResponse result = assertDoesNotThrow(() -> telemetryService.getPhaseDamageChart(MATCH_ID, PLAYER_NAME));

        logger.info("phase damage chart: {}", result);
    }

    @Test
    void getContributeDamageChart() {
        given(matchRepository.findById(MATCH_ID))
                .willReturn(Optional.of(MATCH));

        given(telemetryRepository.existsByMatchId(MATCH_ID))
                .willReturn(true);

        given(telemetryRepository.findLogPlayerKillByMatchIdAndPlayerName(MATCH_ID, PLAYER_NAME))
                .willReturn(OFFICIAL_TELEMETRIES_LOG_PLAYER_KILLS);

        given(telemetryRepository.findLogPlayerTakeDamageByVictimsAndAttacker(eq(MATCH_ID), anyCollection(), anyCollection()))
                .willReturn(OFFICIAL_TELEMETRIES_LOG_PLAYER_TAKE_DAMAGES);

        ContributeDamageChartResponse result = assertDoesNotThrow(() -> telemetryService.getContributeDamageChart(MATCH_ID, PLAYER_NAME));
        logger.info("contribute damage chart: {}", result);
    }

    @Test
    void fetchMultiThread() throws InterruptedException {
        given(matchRepository.findById(MATCH_ID))
                .willReturn(Optional.of(MATCH));

        given(telemetryRepository.existsByMatchId(MATCH_ID))
                .willReturn(false, true);
        List<Runnable> jobs = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(5);

        jobs.add(() -> {
            telemetryService.findKillLogs(MATCH_ID, PLAYER_NAME);
            countDownLatch.countDown();
        });

        jobs.add(() -> {
            telemetryService.findDamageLogByPlayer(MATCH_ID, PLAYER_NAME);
            countDownLatch.countDown();
        });

        jobs.add(() -> {
            telemetryService.findDamagesOfKill(MATCH_ID, PLAYER_NAME);
            countDownLatch.countDown();
        });

        jobs.add(() -> {
            telemetryService.getPhaseDamageChart(MATCH_ID, PLAYER_NAME);
            countDownLatch.countDown();
        });

        jobs.add(() -> {
            telemetryService.getContributeDamageChart(MATCH_ID, PLAYER_NAME);
            countDownLatch.countDown();
        });

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        jobs.forEach(executorService::execute);

        countDownLatch.await();

        verify(pubgService, times(1)).fetchTelemetry(any());
    }
}