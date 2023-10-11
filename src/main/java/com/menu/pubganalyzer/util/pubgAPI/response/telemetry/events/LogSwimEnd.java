package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogSwimEnd extends TelemetryResponse {
    private final CharacterResponse character;
    private final double swimDistance;
    private final double maxSwimDepthOfWater;

    private LogSwimEnd(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.swimDistance = ((Number) origin.get("swimDistance")).doubleValue();
        this.maxSwimDepthOfWater = ((Number) origin.get("maxSwimDepthOfWater")).doubleValue();
    }

    public static LogSwimEnd mappedBy(Map<String, Object> origin) {
        return new LogSwimEnd(origin);
    }
}
