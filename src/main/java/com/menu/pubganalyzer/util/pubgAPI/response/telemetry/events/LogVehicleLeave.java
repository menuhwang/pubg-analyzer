package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class LogVehicleLeave extends TelemetryResponse {
    private CharacterResponse character;
    private VehicleResponse vehicle;
    private double rideDistance;
    private int seatIndex;
    private double maxSpeed;
    private List<CharacterResponse> fellowPassengers;

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
