package com.menu.pubganalyzer.support.fixture;

import com.menu.pubganalyzer.domain.model.telemetries.Telemetry;
import com.menu.pubganalyzer.support.fixture.pubgapi.TelemetryResponseFixture;

import java.util.List;
import java.util.stream.Collectors;

import static com.menu.pubganalyzer.support.fixture.MatchFixture.MATCH_ID;

public class TelemetryFixture {
    public static final List<Telemetry> TELEMETRIES = TelemetryResponseFixture.TELEMETRY_RESPONSES.stream()
            .map(telemetryResponse -> Telemetry.of(MATCH_ID, telemetryResponse))
            .collect(Collectors.toList());

    public static final List<Telemetry> TELEMETRY_LOG_PLAYER_KILLS = TELEMETRIES.stream()
            .filter(telemetry -> "LogPlayerKillV2".equals(telemetry.getType()))
            .collect(Collectors.toList());

    public static final List<Telemetry> TELEMETRY_LOG_PLAYER_TAKE_DAMAGES = TELEMETRIES.stream()
            .filter(telemetry -> "LogPlayerTakeDamage".equals(telemetry.getType()))
            .collect(Collectors.toList());
}
