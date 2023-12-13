package com.menu.pubganalyzer.util.pubg.response.telemetry.events;

import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubg.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class LogObjectInteraction extends TelemetryResponse {
    private CharacterResponse character;
    private String objectType;
    private String objectTypeStatus;
    private List<String> objectTypeAdditionalInfo;

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
