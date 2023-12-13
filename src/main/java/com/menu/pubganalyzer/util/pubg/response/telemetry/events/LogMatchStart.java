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
public class LogMatchStart extends TelemetryResponse {
    private String mapName;
    private String weatherId;
    private List<CharacterWrapperResponse> characters;
    private String cameraViewBehaviour;
    private int teamSize;
    private boolean isCustomGame;
    private boolean isEventMode;
    private String blueZoneCustomOptions;

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
