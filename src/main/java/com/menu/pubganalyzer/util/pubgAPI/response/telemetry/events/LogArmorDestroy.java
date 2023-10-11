package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogArmorDestroy extends TelemetryResponse {
    private final int attackId;
    private final CharacterResponse attacker;
    private final CharacterResponse victim;
    private final String damageTypeCategory;
    private final String damageReason;
    private final String damageCauserName;
    private final ItemResponse item;
    private final float distance;

    private LogArmorDestroy(Map<String, Object> origin) {
        super(origin);
        this.attackId = (int) origin.get("attackId");
        this.attacker = CharacterResponse.mappedBy(origin.get("attacker"));
        this.victim = CharacterResponse.mappedBy(origin.get("victim"));
        this.damageTypeCategory = (String) origin.get("damageTypeCategory");
        this.damageReason = (String) origin.get("damageReason");
        this.damageCauserName = (String) origin.get("damageCauserName");
        this.item = ItemResponse.mappedBy(origin.get("item"));
        this.distance = ((Number) origin.get("distance")).floatValue();
    }

    public static LogArmorDestroy mappedBy(Map<String, Object> origin) {
        return new LogArmorDestroy(origin);
    }
}
