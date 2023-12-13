package com.menu.pubganalyzer.util.pubg.response.telemetry.events;

import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubg.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogItemDrop extends TelemetryResponse {
    private CharacterResponse character;
    private ItemResponse item;

    private LogItemDrop(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.item = ItemResponse.mappedBy(origin.get("item"));
    }

    public static LogItemDrop mappedBy(Map<String, Object> origin) {
        return new LogItemDrop(origin);
    }
}
