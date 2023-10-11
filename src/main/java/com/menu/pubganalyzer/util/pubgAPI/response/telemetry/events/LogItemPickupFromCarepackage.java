package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogItemPickupFromCarepackage extends TelemetryResponse {
    private final CharacterResponse character;
    private final ItemResponse item;
    private final double carePackageUniqueId;

    private LogItemPickupFromCarepackage(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.item = ItemResponse.mappedBy(origin.get("item"));
        this.carePackageUniqueId = ((Number) origin.get("carePackageUniqueId")).doubleValue();
    }

    public static LogItemPickupFromCarepackage mappedBy(Map<String, Object> origin) {
        return new LogItemPickupFromCarepackage(origin);
    }
}

