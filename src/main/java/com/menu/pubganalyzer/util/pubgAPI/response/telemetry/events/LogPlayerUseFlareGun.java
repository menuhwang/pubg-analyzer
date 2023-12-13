package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogPlayerUseFlareGun extends TelemetryResponse {
    private int attackId;
    private int fireWeaponStackCount;
    private CharacterResponse attacker;
    private String attackType;
    private ItemResponse weapon;

    private LogPlayerUseFlareGun(Map<String, Object> origin) {
        super(origin);
        this.attackId = (int) origin.get("attackId");
        this.fireWeaponStackCount = (int) origin.get("fireWeaponStackCount");
        this.attacker = CharacterResponse.mappedBy(origin.get("attacker"));
        this.attackType = (String) origin.get("attackType");
        this.weapon = ItemResponse.mappedBy(origin.get("weapon"));
    }

    public static LogPlayerUseFlareGun mappedBy(Map<String, Object> origin) {
        return new LogPlayerUseFlareGun(origin);
    }
}
