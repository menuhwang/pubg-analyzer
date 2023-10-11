package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogParachuteLanding extends TelemetryResponse {
    private final CharacterResponse character;
    private final double distance;

    private LogParachuteLanding(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.distance = ((Number) origin.get("distance")).doubleValue();
    }

    public static LogParachuteLanding mappedBy(Map<String, Object> origin) {
        return new LogParachuteLanding(origin);
    }
}
