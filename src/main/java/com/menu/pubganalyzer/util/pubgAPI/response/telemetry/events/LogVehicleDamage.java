package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogVehicleDamage extends TelemetryResponse {
    private int attackId;
    private CharacterResponse attacker;
    private VehicleResponse vehicle;
    private String damageTypeCategory;
    private String damageCauserName;
    private double damage;
    private double distance;

    private LogVehicleDamage(Map<String, Object> origin) {
        super(origin);
        this.attackId = (int) origin.get("attackId");
        this.attacker = CharacterResponse.mappedBy(origin.get("attacker"));
        this.vehicle = VehicleResponse.mappedBy(origin.get("vehicle"));
        this.damageTypeCategory = (String) origin.get("damageTypeCategory");
        this.damageCauserName = (String) origin.get("damageCauserName");
        this.damage = ((Number) origin.get("damage")).doubleValue();
        this.distance = ((Number) origin.get("distance")).doubleValue();
    }

    public static LogVehicleDamage mappedBy(Map<String, Object> origin) {
        return new LogVehicleDamage(origin);
    }
}
