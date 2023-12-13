package com.menu.pubganalyzer.util.pubg.response.telemetry.events;

import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubg.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogHeal extends TelemetryResponse {
    private CharacterResponse character;
    private ItemResponse item;
    private float healAmount;

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
