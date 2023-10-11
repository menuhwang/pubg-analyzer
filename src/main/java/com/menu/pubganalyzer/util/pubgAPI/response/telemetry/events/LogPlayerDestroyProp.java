package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogPlayerDestroyProp extends TelemetryResponse {
    private final CharacterResponse attacker;
    private final String objectType;
    private final LocationResponse objectLocation;

    private LogPlayerDestroyProp(Map<String, Object> origin) {
        super(origin);
        this.attacker = CharacterResponse.mappedBy(origin.get("attacker"));
        this.objectType = (String) origin.get("objectType");
        this.objectLocation = LocationResponse.mappedBy(origin.get("objectLocation"));
    }

    public static LogPlayerDestroyProp mappedBy(Map<String, Object> origin) {
        return new LogPlayerDestroyProp(origin);
    }
}
