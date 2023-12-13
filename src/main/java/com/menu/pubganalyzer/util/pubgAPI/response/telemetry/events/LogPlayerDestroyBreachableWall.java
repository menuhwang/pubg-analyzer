package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogPlayerDestroyBreachableWall extends TelemetryResponse {
    private CharacterResponse attacker;
    private ItemResponse weapon;

    private LogPlayerDestroyBreachableWall(Map<String, Object> origin) {
        super(origin);
        this.attacker = CharacterResponse.mappedBy(origin.get("attacker"));
        this.weapon = ItemResponse.mappedBy(origin.get("weapon"));
    }

    public static LogPlayerDestroyBreachableWall mappedBy(Map<String, Object> origin) {
        return new LogPlayerDestroyBreachableWall(origin);
    }
}
