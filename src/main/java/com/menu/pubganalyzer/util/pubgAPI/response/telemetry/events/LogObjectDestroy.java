package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogObjectDestroy extends TelemetryResponse {
    private CharacterResponse character;
    private String objectType;
    private LocationResponse objectLocation;

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
