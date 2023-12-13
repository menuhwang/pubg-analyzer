package com.menu.pubganalyzer.util.pubg.response.telemetry.events;

import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubg.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogPlayerPosition extends TelemetryResponse {
    private CharacterResponse character;
    private VehicleResponse vehicle;
    private double elapsedTime;
    private int numAlivePlayers;

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
