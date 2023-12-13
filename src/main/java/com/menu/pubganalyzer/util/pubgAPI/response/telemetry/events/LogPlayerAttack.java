package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogPlayerAttack extends TelemetryResponse {
    private int attackId;
    private int fireWeaponStackCount;
    private CharacterResponse attacker;
    private String attackType;
    private ItemResponse weapon;
    private VehicleResponse vehicle;

    private LogPlayerAttack(Map<String, Object> origin) {
        super(origin);
        this.attackId = (int) origin.get("attackId");
        this.fireWeaponStackCount = (int) origin.get("fireWeaponStackCount");
        this.attacker = CharacterResponse.mappedBy(origin.get("attacker"));
        this.attackType = (String) origin.get("attackType");
        this.weapon = ItemResponse.mappedBy(origin.get("weapon"));
        this.vehicle = VehicleResponse.mappedBy(origin.get("vehicle"));
    }

    public static LogPlayerAttack mappedBy(Map<String, Object> origin) {
        return new LogPlayerAttack(origin);
    }
}
