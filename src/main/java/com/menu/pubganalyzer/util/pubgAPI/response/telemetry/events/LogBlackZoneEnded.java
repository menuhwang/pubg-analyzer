package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class LogBlackZoneEnded extends TelemetryResponse {
    private final List<CharacterResponse> survivors;

    @SuppressWarnings("unchecked")
    private LogBlackZoneEnded(Map<String, Object> origin) {
        super(origin);

        this.survivors = ((List<Object>) origin.get("survivors")).stream()
                .map(CharacterResponse::mappedBy)
                .collect(Collectors.toList());
    }

    public static LogBlackZoneEnded mappedBy(Map<String, Object> origin) {
        return new LogBlackZoneEnded(origin);
    }
}
