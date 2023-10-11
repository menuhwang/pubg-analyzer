package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogPlayerPosition extends TelemetryResponse {
    private final CharacterResponse character;
    private final VehicleResponse vehicle;
    private final double elapsedTime;
    private final int numAlivePlayers;

    private LogPlayerPosition(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.vehicle = VehicleResponse.mappedBy(origin.get("vehicle"));
        this.elapsedTime = ((Number) origin.get("elapsedTime")).doubleValue();
        this.numAlivePlayers = (int) origin.get("numAlivePlayers");
    }

    public static LogPlayerPosition mappedBy(Map<String, Object> origin) {
        return new LogPlayerPosition(origin);
    }
}
