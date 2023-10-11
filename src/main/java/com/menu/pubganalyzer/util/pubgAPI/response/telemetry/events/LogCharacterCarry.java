package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogCharacterCarry extends TelemetryResponse {
    private final CharacterResponse character;
    private final String carryState;

    private LogCharacterCarry(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.carryState = (String) origin.get("carryState");
    }

    public static LogCharacterCarry mappedBy(Map<String, Object> origin) {
        return new LogCharacterCarry(origin);
    }
}
