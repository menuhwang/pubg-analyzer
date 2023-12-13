package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogPlayerDestroyProp extends TelemetryResponse {
    private CharacterResponse attacker;
    private String objectType;
    private LocationResponse objectLocation;

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
