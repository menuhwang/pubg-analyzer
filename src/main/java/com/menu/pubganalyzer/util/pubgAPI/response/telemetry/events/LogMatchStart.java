package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class LogMatchStart extends TelemetryResponse {
    private final String mapName;
    private final String weatherId;
    private final List<CharacterWrapperResponse> characters;
    private final String cameraViewBehaviour;
    private final int teamSize;
    private final boolean isCustomGame;
    private final boolean isEventMode;
    private final String blueZoneCustomOptions;

    @SuppressWarnings("unchecked")
    private LogMatchStart(Map<String, Object> origin) {
        super(origin);
        this.mapName = (String) origin.get("mapName");
        this.weatherId = (String) origin.get("weatherId");
        this.characters = ((List<Object>) origin.get("characters")).stream()
                .map(CharacterWrapperResponse::mappedBy)
                .collect(Collectors.toList());
        this.cameraViewBehaviour = (String) origin.get("cameraViewBehaviour");
        this.teamSize = (int) origin.get("teamSize");
        this.isCustomGame = (boolean) origin.get("isCustomGame");
        this.isEventMode = (boolean) origin.get("isEventMode");
        this.blueZoneCustomOptions = (String) origin.get("blueZoneCustomOptions");
    }

    public static LogMatchStart mappedBy(Map<String, Object> origin) {
        return new LogMatchStart(origin);
    }
}
