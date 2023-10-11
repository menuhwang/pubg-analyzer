package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class LogEmPickupLiftOff extends TelemetryResponse {
    private final CharacterResponse instigator;
    private final List<CharacterResponse> riders;

    @SuppressWarnings("unchecked")
    private LogEmPickupLiftOff(Map<String, Object> origin) {
        super(origin);
        this.instigator = CharacterResponse.mappedBy(origin.get("instigator"));
        this.riders = ((List<Object>) origin.get("riders")).stream()
                .map(CharacterResponse::mappedBy)
                .collect(Collectors.toList());
    }

    public static LogEmPickupLiftOff mappedBy(Map<String, Object> origin) {
        return new LogEmPickupLiftOff(origin);
    }
}
