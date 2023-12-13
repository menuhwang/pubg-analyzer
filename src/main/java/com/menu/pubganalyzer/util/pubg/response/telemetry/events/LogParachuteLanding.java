package com.menu.pubganalyzer.util.pubg.response.telemetry.events;

import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubg.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogParachuteLanding extends TelemetryResponse {
    private CharacterResponse character;
    private double distance;

    private LogParachuteLanding(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.distance = ((Number) origin.get("distance")).doubleValue();
    }

    public static LogParachuteLanding mappedBy(Map<String, Object> origin) {
        return new LogParachuteLanding(origin);
    }
}
