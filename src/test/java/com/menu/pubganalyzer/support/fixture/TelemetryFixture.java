package com.menu.pubganalyzer.support.fixture;

import com.menu.pubganalyzer.domain.model.telemetries.Telemetry;
import com.menu.pubganalyzer.support.fixture.pubgapi.TelemetryResponseFixture;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.CharacterResponse;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.menu.pubganalyzer.support.fixture.PlayerFixture.PLAYER_NAME;

public class TelemetryFixture {
    public static final List<Telemetry> OFFICIAL_TELEMETRIES = TelemetryResponseFixture.OFFICIAL_TELEMETRY_RESPONSE.stream()
            .map(Telemetry::from)
            .collect(Collectors.toList());

    public static final List<Telemetry> OFFICIAL_TELEMETRIES_LOG_PLAYER_KILLS = OFFICIAL_TELEMETRIES.stream()
            .filter(telemetry -> "LogPlayerKillV2".equals(telemetry.getType()))
            .filter(telemetry -> {
                Map<String, Object> attribute = telemetry.getAttribute();
                if (!attribute.containsKey("killer")) return false;
                CharacterResponse killer = (CharacterResponse) attribute.get("killer");
                if (Objects.isNull(killer)) return false;
                return killer.getName().equals(PLAYER_NAME);
            })
            .collect(Collectors.toList());

    public static final List<Telemetry> OFFICIAL_TELEMETRIES_LOG_PLAYER_TAKE_DAMAGES = OFFICIAL_TELEMETRIES.stream()
            .filter(telemetry -> "LogPlayerTakeDamage".equals(telemetry.getType()))
            .filter(telemetry -> {
                Map<String, Object> attribute = telemetry.getAttribute();

                if (!attribute.containsKey("attacker")) return false;
                CharacterResponse attacker = (CharacterResponse) attribute.get("attacker");
                if (Objects.isNull(attacker)) return false;

                if (!attribute.containsKey("victim")) return false;
                CharacterResponse victim = (CharacterResponse) attribute.get("victim");
                if (Objects.isNull(victim)) return false;

                if (!attacker.getName().equals(PLAYER_NAME)) return false;

                return !victim.getName().equals(PLAYER_NAME);
            })
            .collect(Collectors.toList());
}
