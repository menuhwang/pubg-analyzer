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
public class LogMatchEnd extends TelemetryResponse {
    private List<CharacterWrapperResponse> characters;
    private GameResultOnFinishedResponse gameResultOnFinished;

    @SuppressWarnings("unchecked")
    private LogMatchEnd(Map<String, Object> origin) {
        super(origin);
        this.characters = ((List<Object>) origin.get("characters")).stream()
                .map(CharacterWrapperResponse::mappedBy)
                .collect(Collectors.toList());
        this.gameResultOnFinished = GameResultOnFinishedResponse.mappedBy(origin.get("gameResultOnFinished"));
    }

    public static LogMatchEnd mappedBy(Map<String, Object> origin) {
        return new LogMatchEnd(origin);
    }
}
