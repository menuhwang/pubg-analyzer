package com.menu.pubganalyzer.support.fixture;

import com.menu.pubganalyzer.domain.model.telemetries.LogPlayerKillV2;
import com.menu.pubganalyzer.domain.model.telemetries.impl.LogPlayerKillV2Impl;

import java.util.List;
import java.util.stream.Collectors;

import static com.menu.pubganalyzer.support.fixture.TelemetryFixture.TELEMETRY_LOG_PLAYER_KILLS;

public class LogPlayerKillFixture {
    public static final List<LogPlayerKillV2> LOG_PLAYER_KILLS = TELEMETRY_LOG_PLAYER_KILLS.stream()
            .map(LogPlayerKillV2Impl::new)
            .collect(Collectors.toList());
}
