package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogWheelDestroy extends TelemetryResponse {
    private final int attackId;
    private final CharacterResponse attacker;
    private final VehicleResponse vehicle;
    private final String damageTypeCategory;
    private final String damageCauserName;

    private LogWheelDestroy(Map<String, Object> origin) {
        super(origin);
        this.attackId = (int) origin.get("attackId");
        this.attacker = CharacterResponse.mappedBy(origin.get("attacker"));
        this.vehicle = VehicleResponse.mappedBy(origin.get("vehicle"));
        this.damageTypeCategory = (String) origin.get("damageTypeCategory");
        this.damageCauserName = (String) origin.get("damageCauserName");
    }

    public static LogWheelDestroy mappedBy(Map<String, Object> origin) {
        return new LogWheelDestroy(origin);
    }
}
