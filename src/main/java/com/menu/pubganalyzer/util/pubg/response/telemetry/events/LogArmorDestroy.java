package com.menu.pubganalyzer.util.pubg.response.telemetry.events;

import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubg.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogArmorDestroy extends TelemetryResponse {
    private int attackId;
    private CharacterResponse attacker;
    private CharacterResponse victim;
    private String damageTypeCategory;
    private String damageReason;
    private String damageCauserName;
    private ItemResponse item;
    private float distance;

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
