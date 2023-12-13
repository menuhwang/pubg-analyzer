package com.menu.pubganalyzer.util.pubg.response.telemetry.events;

import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubg.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogItemPickupFromCarepackage extends TelemetryResponse {
    private CharacterResponse character;
    private ItemResponse item;
    private double carePackageUniqueId;

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

