package com.menu.pubganalyzer.support.fixture;

import com.menu.pubganalyzer.support.fixture.pubgapi.TelemetryResponseFixture;
import com.menu.pubganalyzer.telemetries.model.TelemetryEntity;
import com.menu.pubganalyzer.util.pubg.response.telemetry.events.LogPlayerKillV2;
import com.menu.pubganalyzer.util.pubg.response.telemetry.events.LogPlayerTakeDamage;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.menu.pubganalyzer.support.fixture.PlayerFixture.PLAYER_NAME;
import static com.menu.pubganalyzer.support.fixture.pubgapi.MatchResponseFixture.MATCH_ID;

public class TelemetryFixture {
    public static final List<TelemetryEntity> OFFICIAL_TELEMETRIES = TelemetryResponseFixture.OFFICIAL_TELEMETRY_RESPONSE.stream()
            .map(telemetryResponse -> TelemetryEntity.of(MATCH_ID, telemetryResponse))
            .collect(Collectors.toList());

    public static final List<TelemetryEntity> OFFICIAL_TELEMETRIES_LOG_PLAYER_KILLS = OFFICIAL_TELEMETRIES.stream()
            .filter(telemetryEntity -> "LogPlayerKillV2".equals(telemetryEntity.getTelemetry().getType()))
            .filter(telemetryEntity -> {
                LogPlayerKillV2 logPlayerKillV2 = (LogPlayerKillV2) telemetryEntity.getTelemetry();
                return Objects.nonNull(logPlayerKillV2.getKiller()) && PLAYER_NAME.equals(logPlayerKillV2.getKiller().getName());
            })
            .collect(Collectors.toList());

    public static final List<TelemetryEntity> OFFICIAL_TELEMETRIES_LOG_PLAYER_TAKE_DAMAGES = OFFICIAL_TELEMETRIES.stream()
            .filter(telemetryEntity -> "LogPlayerTakeDamage".equals(telemetryEntity.getTelemetry().getType()))
            .filter(telemetryEntity -> {
                LogPlayerTakeDamage logPlayerTakeDamage = (LogPlayerTakeDamage) telemetryEntity.getTelemetry();
                return Objects.nonNull(logPlayerTakeDamage.getAttacker()) && !PLAYER_NAME.equals(logPlayerTakeDamage.getVictim().getName());
            })
            .collect(Collectors.toList());
}
