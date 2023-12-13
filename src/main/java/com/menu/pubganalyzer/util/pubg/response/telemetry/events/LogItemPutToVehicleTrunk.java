package com.menu.pubganalyzer.util.pubg.response.telemetry.events;

import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubg.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogItemPutToVehicleTrunk extends TelemetryResponse {
    private CharacterResponse character;
    private VehicleResponse vehicle;
    private ItemResponse item;

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

