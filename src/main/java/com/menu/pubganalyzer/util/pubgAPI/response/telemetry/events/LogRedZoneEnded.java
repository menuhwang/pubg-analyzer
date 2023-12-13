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
public class LogRedZoneEnded extends TelemetryResponse {
    private List<CharacterResponse> drivers;

    @SuppressWarnings("unchecked")
    private LogRedZoneEnded(Map<String, Object> origin) {
        super(origin);
        this.drivers = ((List<Object>) origin.get("drivers")).stream()
                .map(CharacterResponse::mappedBy)
                .collect(Collectors.toList());
    }

    public static LogRedZoneEnded mappedBy(Map<String, Object> origin) {
        return new LogRedZoneEnded(origin);
    }
}
