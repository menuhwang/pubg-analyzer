package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class LogPlayerKill extends TelemetryResponse {
    private final int attackId;
    private final CharacterResponse killer;
    private final CharacterResponse victim;
    private final CharacterResponse assistant;
    private final int dBNOId;
    private final String damageReason;
    private final String damageTypeCategory;
    private final String damageCauserName;
    private final List<String> damageCauserAdditionalInfo;
    private final String victimWeapon;
    private final List<String> victimWeaponAdditionalInfo;
    private final double distance;
    private final GameResultResponse victimGameResult;
    private final boolean isThroughPenetrableWall;

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
