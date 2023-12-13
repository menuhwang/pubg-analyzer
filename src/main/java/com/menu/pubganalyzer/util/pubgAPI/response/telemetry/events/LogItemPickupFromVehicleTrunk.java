package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogItemPickupFromVehicleTrunk extends TelemetryResponse {
    private CharacterResponse character;
    private VehicleResponse vehicle;
    private ItemResponse item;

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

