package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogItemPickupFromVehicleTrunk extends TelemetryResponse {
    private final CharacterResponse character;
    private final VehicleResponse vehicle;
    private final ItemResponse item;

    private LogItemPickupFromVehicleTrunk(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.vehicle = VehicleResponse.mappedBy(origin.get("vehicle"));
        this.item = ItemResponse.mappedBy(origin.get("item"));
    }

    public static LogItemPickupFromVehicleTrunk mappedBy(Map<String, Object> origin) {
        return new LogItemPickupFromVehicleTrunk(origin);
    }
}

