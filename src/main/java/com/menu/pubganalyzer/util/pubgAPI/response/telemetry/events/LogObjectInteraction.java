package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class LogObjectInteraction extends TelemetryResponse {
    private final CharacterResponse character;
    private final String objectType;
    private final String objectTypeStatus;
    private final List<String> objectTypeAdditionalInfo;

    @SuppressWarnings("unchecked")
    private LogObjectInteraction(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.objectType = (String) origin.get("objectType");
        this.objectTypeStatus = (String) origin.get("objectTypeStatus");
        this.objectTypeAdditionalInfo = (List<String>) origin.get("objectTypeAdditionalInfo");
    }

    public static LogObjectInteraction mappedBy(Map<String, Object> origin) {
        return new LogObjectInteraction(origin);
    }
}
