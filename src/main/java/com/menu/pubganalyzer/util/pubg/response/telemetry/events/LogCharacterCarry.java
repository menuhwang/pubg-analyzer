package com.menu.pubganalyzer.util.pubg.response.telemetry.events;

import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubg.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogCharacterCarry extends TelemetryResponse {
    private CharacterResponse character;
    private String carryState;

    private LogCharacterCarry(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.carryState = (String) origin.get("carryState");
    }

    public static LogCharacterCarry mappedBy(Map<String, Object> origin) {
        return new LogCharacterCarry(origin);
    }
}
