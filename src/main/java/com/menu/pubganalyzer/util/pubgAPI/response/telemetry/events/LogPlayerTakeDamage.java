package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogPlayerTakeDamage extends TelemetryResponse {
    private int attackId;
    private CharacterResponse attacker;
    private CharacterResponse victim;
    private String damageTypeCategory;
    private String damageReason;
    private double damage;
    private String damageCauserName;
    private boolean isThroughPenetrableWall;

    private LogPlayerTakeDamage(Map<String, Object> origin) {
        super(origin);
        this.attackId = (int) origin.get("attackId");
        this.attacker = CharacterResponse.mappedBy(origin.get("attacker"));
        this.victim = CharacterResponse.mappedBy(origin.get("victim"));
        this.damageTypeCategory = (String) origin.get("damageTypeCategory");
        this.damageReason = (String) origin.get("damageReason");
        this.damage = ((Number) origin.get("damage")).doubleValue();
        this.damageCauserName = (String) origin.get("damageCauserName");
        this.isThroughPenetrableWall = (boolean) origin.get("isThroughPenetrableWall");
    }

    public static LogPlayerTakeDamage mappedBy(Map<String, Object> origin) {
        return new LogPlayerTakeDamage(origin);
    }
}
