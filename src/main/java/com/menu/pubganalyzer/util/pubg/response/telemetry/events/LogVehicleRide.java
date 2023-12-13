package com.menu.pubganalyzer.util.pubg.response.telemetry.events;

import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubg.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class LogVehicleRide extends TelemetryResponse {
    private CharacterResponse character;
    private VehicleResponse vehicle;
    private int seatIndex;
    private List<CharacterResponse> fellowPassengers;

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
