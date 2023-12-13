package com.menu.pubganalyzer.util.pubg.response.telemetry.events;

import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubg.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogSwimStart extends TelemetryResponse {
    private CharacterResponse character;

    private LogSwimStart(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
    }

    public static LogSwimStart mappedBy(Map<String, Object> origin) {
        return new LogSwimStart(origin);
    }
}
