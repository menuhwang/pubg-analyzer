package com.menu.pubganalyzer.util.pubg.response.telemetry.events;

import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubg.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class LogPlayerKill extends TelemetryResponse {
    private int attackId;
    private CharacterResponse killer;
    private CharacterResponse victim;
    private CharacterResponse assistant;
    private int dBNOId;
    private String damageReason;
    private String damageTypeCategory;
    private String damageCauserName;
    private List<String> damageCauserAdditionalInfo;
    private String victimWeapon;
    private List<String> victimWeaponAdditionalInfo;
    private double distance;
    private GameResultResponse victimGameResult;
    private boolean isThroughPenetrableWall;

    @SuppressWarnings("unchecked")
    private LogPlayerKill(Map<String, Object> origin) {
        super(origin);
        this.attackId = (int) origin.get("attackId");
        this.killer = CharacterResponse.mappedBy(origin.get("killer"));
        this.victim = CharacterResponse.mappedBy(origin.get("victim"));
        this.assistant = CharacterResponse.mappedBy(origin.get("assistant"));
        this.dBNOId = (int) origin.get("dBNOId");
        this.damageReason = (String) origin.get("damageReason");
        this.damageTypeCategory = (String) origin.get("damageTypeCategory");
        this.damageCauserName = (String) origin.get("damageCauserName");
        this.damageCauserAdditionalInfo = (List<String>) origin.get("damageCauserAdditionalInfo");
        this.victimWeapon = (String) origin.get("victimWeapon");
        this.victimWeaponAdditionalInfo = (List<String>) origin.get("victimWeaponAdditionalInfo");
        this.distance = ((Number) origin.get("distance")).doubleValue();
        this.victimGameResult = GameResultResponse.mappedBy(origin.get("victimGameResult"));
        this.isThroughPenetrableWall = (boolean) origin.get("isThroughPenetrableWall");
    }

    public static LogPlayerKill mappedBy(Map<String, Object> origin) {
        return new LogPlayerKill(origin);
    }
}
