package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogPlayerDestroyBreachableWall extends TelemetryResponse {
    private final CharacterResponse attacker;
    private final ItemResponse weapon;

    private LogPlayerDestroyBreachableWall(Map<String, Object> origin) {
        super(origin);
        this.attacker = CharacterResponse.mappedBy(origin.get("attacker"));
        this.weapon = ItemResponse.mappedBy(origin.get("weapon"));
    }

    public static LogPlayerDestroyBreachableWall mappedBy(Map<String, Object> origin) {
        return new LogPlayerDestroyBreachableWall(origin);
    }
}
