package com.menu.pubganalyzer.util.pubg.response.telemetry.events;

import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubg.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogItemDetach extends TelemetryResponse {
    private CharacterResponse character;
    private ItemResponse parentItem;
    private ItemResponse childItem;

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
