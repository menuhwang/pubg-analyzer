package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogObjectDestroy extends TelemetryResponse {
    private final CharacterResponse character;
    private final String objectType;
    private final LocationResponse objectLocation;

    private LogObjectDestroy(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.objectType = (String) origin.get("objectType");
        this.objectLocation = LocationResponse.mappedBy(origin.get("objectLocation"));
    }

    public static LogObjectDestroy mappedBy(Map<String, Object> origin) {
        return new LogObjectDestroy(origin);
    }
}
