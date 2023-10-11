package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogPlayerCreate extends TelemetryResponse {
    private final CharacterResponse character;

    private LogPlayerCreate(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
    }

    public static LogPlayerCreate mappedBy(Map<String, Object> origin) {
        return new LogPlayerCreate(origin);
    }
}
