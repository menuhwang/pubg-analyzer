package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogGameStatePeriodic extends TelemetryResponse {
    private final GameStateResponse gameState;

    private LogGameStatePeriodic(Map<String, Object> origin) {
        super(origin);
        this.gameState = GameStateResponse.mappedBy(origin.get("gameState"));
    }

    public static LogGameStatePeriodic mappedBy(Map<String, Object> origin) {
        return new LogGameStatePeriodic(origin);
    }
}
