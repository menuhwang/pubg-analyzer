package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogVaultStart extends TelemetryResponse {
    private CharacterResponse character;
    private boolean isLedgeGrab;

    private LogVaultStart(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.isLedgeGrab = (boolean) origin.get("isLedgeGrab");
    }

    public static LogVaultStart mappedBy(Map<String, Object> origin) {
        return new LogVaultStart(origin);
    }
}
