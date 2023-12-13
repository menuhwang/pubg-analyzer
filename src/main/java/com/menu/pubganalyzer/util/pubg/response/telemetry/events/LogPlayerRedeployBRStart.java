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
public class LogPlayerRedeployBRStart extends TelemetryResponse {
    private List<CharacterResponse> characters;

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
