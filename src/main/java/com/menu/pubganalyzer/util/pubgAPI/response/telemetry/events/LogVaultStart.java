package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogVaultStart extends TelemetryResponse {
    private final CharacterResponse character;
    private final boolean isLedgeGrab;

    private LogVaultStart(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.isLedgeGrab = (boolean) origin.get("isLedgeGrab");
    }

    public static LogVaultStart mappedBy(Map<String, Object> origin) {
        return new LogVaultStart(origin);
    }
}
