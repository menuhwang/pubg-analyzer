package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.model.matches.Match;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.menu.pubganalyzer.support.fixture.LogPlayerKillFixture.LOG_PLAYER_KILLS;
import static com.menu.pubganalyzer.support.fixture.LogPlayerTakeDamageFixture.LOG_PLAYER_TAKE_DAMAGES;
import static com.menu.pubganalyzer.support.fixture.MatchFixture.MATCH;
import static com.menu.pubganalyzer.support.fixture.PlayerFixture.PLAYER_NAME;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class ReportServiceTest {
    private final TelemetryService telemetryService = Mockito.mock(TelemetryService.class);
    private final ReportService reportService = new ReportService(telemetryService);

    @Test
    void getMatchReport() {
        given(telemetryService.findKillLogs(any(Match.class), eq(PLAYER_NAME)))
                .willReturn(LOG_PLAYER_KILLS);
        given(telemetryService.findDamageLogs(any(Match.class),  anyCollection(), anyCollection()))
                .willReturn(LOG_PLAYER_TAKE_DAMAGES);
        given(telemetryService.findDamageLogs(any(Match.class),  eq(PLAYER_NAME)))
                .willReturn(LOG_PLAYER_TAKE_DAMAGES);

        assertDoesNotThrow(() -> reportService.getMatchReport(MATCH, PLAYER_NAME));

        verify(telemetryService).findKillLogs(MATCH, PLAYER_NAME);
        verify(telemetryService).findDamageLogs(any(Match.class), anyCollection(), anyCollection());
        verify(telemetryService).findDamageLogs(MATCH, PLAYER_NAME);
    }
}