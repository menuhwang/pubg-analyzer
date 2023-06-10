package com.menu.pubganalyzer.support.fixture;

import com.menu.pubganalyzer.domain.Telemetry;
import com.menu.pubganalyzer.domain.model.LogPlayerKillV2;
import com.menu.pubganalyzer.domain.model.LogPlayerTakeDamage;
import com.menu.pubganalyzer.support.fixture.pubgapi.MatchResponseFixture;
import com.menu.pubganalyzer.support.fixture.pubgapi.TelemetryResponseFixture;

public class TelemetryFixture {
    private static final String MATCH_ID = MatchResponseFixture.MATCH_ID;
    public static final LogPlayerKillV2 LOG_PLAYER_KILL = LogPlayerKillV2.of(TelemetryResponseFixture.RES_LOG_PLAYER_KILL, MATCH_ID);
    public static final LogPlayerTakeDamage LOG_PLAYER_TAKE_DAMAGE = LogPlayerTakeDamage.of(TelemetryResponseFixture.RES_LOG_PLAYER_TAKE_DAMAGE, MATCH_ID);
    public static final Telemetry TELEMETRY = Telemetry.of(TelemetryResponseFixture.TELEMETRY_RESPONSES, MATCH_ID);
}
