package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class LogVehicleRide extends TelemetryResponse {
    private final CharacterResponse character;
    private final VehicleResponse vehicle;
    private final int seatIndex;
    private final List<CharacterResponse> fellowPassengers;

    @SuppressWarnings("unchecked")
    private LogVehicleRide(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.vehicle = VehicleResponse.mappedBy(origin.get("vehicle"));
        this.seatIndex = (int) origin.get("seatIndex");
        this.fellowPassengers = ((List<Object>) origin.get("fellowPassengers")).stream()
                .map(CharacterResponse::mappedBy)
                .collect(Collectors.toList());
    }

    public static LogVehicleRide mappedBy(Map<String, Object> origin) {
        return new LogVehicleRide(origin);
    }
}
