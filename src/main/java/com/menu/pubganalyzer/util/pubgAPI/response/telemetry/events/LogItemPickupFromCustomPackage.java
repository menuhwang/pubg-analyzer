package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogItemPickupFromCustomPackage extends TelemetryResponse {
    private final CharacterResponse character;
    private final ItemResponse item;

    private LogItemPickupFromCustomPackage(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.item = ItemResponse.mappedBy(origin.get("item"));
    }

    public static LogItemPickupFromCustomPackage mappedBy(Map<String, Object> origin) {
        return new LogItemPickupFromCustomPackage(origin);
    }
}

