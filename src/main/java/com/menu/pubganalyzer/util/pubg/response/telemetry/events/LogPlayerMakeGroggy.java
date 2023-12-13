package com.menu.pubganalyzer.util.pubg.response.telemetry.events;

import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubg.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class LogPlayerMakeGroggy extends TelemetryResponse {
    private int attackId;
    private CharacterResponse attacker;
    private CharacterResponse victim;
    private String damageReason;
    private String damageTypeCategory;
    private String damageCauserName;
    private List<String> damageCauserAdditionalInfo;
    private String VictimWeapon;
    private List<String> VictimWeaponAdditionalInfo;
    private double distance;
    private boolean isAttackerInVehicle;
    private int dBNOId;
    private boolean isThroughPenetrableWall;

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
