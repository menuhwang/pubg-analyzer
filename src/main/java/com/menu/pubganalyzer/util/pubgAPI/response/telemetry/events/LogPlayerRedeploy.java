package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogPlayerRedeploy extends TelemetryResponse {
    private final CharacterResponse character;

    private LogPlayerRedeploy(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
    }

    public static LogPlayerRedeploy mappedBy(Map<String, Object> origin) {
        return new LogPlayerRedeploy(origin);
    }
}
