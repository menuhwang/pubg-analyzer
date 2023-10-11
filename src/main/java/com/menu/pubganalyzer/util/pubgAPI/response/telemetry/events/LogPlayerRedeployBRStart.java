package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class LogPlayerRedeployBRStart extends TelemetryResponse {
    private final List<CharacterResponse> characters;

    @SuppressWarnings("unchecked")
    private LogPlayerRedeployBRStart(Map<String, Object> origin) {
        super(origin);
        this.characters = ((List<Object>) origin.get("characters")).stream()
                .map(CharacterResponse::mappedBy)
                .collect(Collectors.toList());
    }

    public static LogPlayerRedeployBRStart mappedBy(Map<String, Object> origin) {
        return new LogPlayerRedeployBRStart(origin);
    }
}
