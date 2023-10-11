package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogItemEquip extends TelemetryResponse {
    private final CharacterResponse character;
    private final ItemResponse item;

    private LogItemEquip(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.item = ItemResponse.mappedBy(origin.get("item"));
    }

    public static LogItemEquip mappedBy(Map<String, Object> origin) {
        return new LogItemEquip(origin);
    }
}

