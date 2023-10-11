package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class LogVehicleLeave extends TelemetryResponse {
    private final CharacterResponse character;
    private final VehicleResponse vehicle;
    private final double rideDistance;
    private final int seatIndex;
    private final double maxSpeed;
    private final List<CharacterResponse> fellowPassengers;

    @SuppressWarnings("unchecked")
    private LogVehicleLeave(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.vehicle = VehicleResponse.mappedBy(origin.get("vehicle"));
        this.rideDistance = ((Number) origin.get("rideDistance")).doubleValue();
        this.seatIndex = (int) origin.get("seatIndex");
        this.maxSpeed = ((Number) origin.get("maxSpeed")).doubleValue();
        this.fellowPassengers = ((List<Object>) origin.get("fellowPassengers")).stream()
                .map(CharacterResponse::mappedBy)
                .collect(Collectors.toList());
    }

    public static LogVehicleLeave mappedBy(Map<String, Object> origin) {
        return new LogVehicleLeave(origin);
    }
}
