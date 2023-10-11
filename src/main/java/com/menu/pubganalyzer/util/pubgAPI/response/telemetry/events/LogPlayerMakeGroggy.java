package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class LogPlayerMakeGroggy extends TelemetryResponse {
    private final int attackId;
    private final CharacterResponse attacker;
    private final CharacterResponse victim;
    private final String damageReason;
    private final String damageTypeCategory;
    private final String damageCauserName;
    private final List<String> damageCauserAdditionalInfo;
    private final String VictimWeapon;
    private final List<String> VictimWeaponAdditionalInfo;
    private final double distance;
    private final boolean isAttackerInVehicle;
    private final int dBNOId;
    private final boolean isThroughPenetrableWall;

    @SuppressWarnings("unchecked")
    private LogPlayerMakeGroggy(Map<String, Object> origin) {
        super(origin);
        this.attackId = (int) origin.get("attackId");
        this.attacker = CharacterResponse.mappedBy(origin.get("attacker"));
        this.victim = CharacterResponse.mappedBy(origin.get("victim"));
        this.damageReason = (String) origin.get("damageReason");
        this.damageTypeCategory = (String) origin.get("damageTypeCategory");
        this.damageCauserName = (String) origin.get("damageCauserName");
        this.damageCauserAdditionalInfo = (List<String>) origin.get("damageCauserAdditionalInfo");
        this.VictimWeapon = (String) origin.get("VictimWeapon");
        this.VictimWeaponAdditionalInfo = (List<String>) origin.get("VictimWeaponAdditionalInfo");
        this.distance = ((Number) origin.get("distance")).doubleValue();
        this.isAttackerInVehicle = (boolean) origin.get("isAttackerInVehicle");
        this.dBNOId = (int) origin.get("dBNOId");
        this.isThroughPenetrableWall = (boolean) origin.get("isThroughPenetrableWall");
    }

    public static LogPlayerMakeGroggy mappedBy(Map<String, Object> origin) {
        return new LogPlayerMakeGroggy(origin);
    }
}
