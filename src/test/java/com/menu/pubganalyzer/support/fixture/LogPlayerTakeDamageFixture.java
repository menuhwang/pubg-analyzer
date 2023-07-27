package com.menu.pubganalyzer.support.fixture;

import com.menu.pubganalyzer.domain.model.telemetries.LogPlayerTakeDamage;
import com.menu.pubganalyzer.domain.model.telemetries.impl.LogPlayerTakeDamageImpl;

import java.util.List;
import java.util.stream.Collectors;

import static com.menu.pubganalyzer.support.fixture.TelemetryFixture.TELEMETRY_LOG_PLAYER_TAKE_DAMAGES;

public class LogPlayerTakeDamageFixture {
    public static final List<LogPlayerTakeDamage> LOG_PLAYER_TAKE_DAMAGES = TELEMETRY_LOG_PLAYER_TAKE_DAMAGES.stream()
            .map(LogPlayerTakeDamageImpl::new)
            .collect(Collectors.toList());
}
