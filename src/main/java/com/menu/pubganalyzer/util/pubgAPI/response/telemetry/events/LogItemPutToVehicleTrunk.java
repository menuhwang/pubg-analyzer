package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogItemPutToVehicleTrunk extends TelemetryResponse {
    private final CharacterResponse character;
    private final VehicleResponse vehicle;
    private final ItemResponse item;

    private LogItemPutToVehicleTrunk(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.vehicle = VehicleResponse.mappedBy(origin.get("vehicle"));
        this.item = ItemResponse.mappedBy(origin.get("item"));
    }

    public static LogItemPutToVehicleTrunk mappedBy(Map<String, Object> origin) {
        return new LogItemPutToVehicleTrunk(origin);
    }
}

