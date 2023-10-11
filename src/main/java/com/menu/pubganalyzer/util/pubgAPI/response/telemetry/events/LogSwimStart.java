package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogSwimStart extends TelemetryResponse {
    private final CharacterResponse character;

    private LogSwimStart(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
    }

    public static LogSwimStart mappedBy(Map<String, Object> origin) {
        return new LogSwimStart(origin);
    }
}
