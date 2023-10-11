package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogHeal extends TelemetryResponse {
    private final CharacterResponse character;
    private final ItemResponse item;
    private final float healAmount;

    private LogHeal(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.item = ItemResponse.mappedBy(origin.get("item"));
        this.healAmount = ((Number) origin.get("healAmount")).floatValue();
    }

    public static LogHeal mappedBy(Map<String, Object> origin) {
        return new LogHeal(origin);
    }
}
