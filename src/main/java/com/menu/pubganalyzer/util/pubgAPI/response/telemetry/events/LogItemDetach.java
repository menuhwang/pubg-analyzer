package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogItemDetach extends TelemetryResponse {
    private final CharacterResponse character;
    private final ItemResponse parentItem;
    private final ItemResponse childItem;

    private LogItemDetach(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.parentItem = ItemResponse.mappedBy(origin.get("parentItem"));
        this.childItem = ItemResponse.mappedBy(origin.get("childItem"));
    }

    public static LogItemDetach mappedBy(Map<String, Object> origin) {
        return new LogItemDetach(origin);
    }
}
