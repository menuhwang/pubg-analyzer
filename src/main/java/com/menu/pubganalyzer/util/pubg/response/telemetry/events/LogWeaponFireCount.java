package com.menu.pubganalyzer.util.pubg.response.telemetry.events;

import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubg.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogWeaponFireCount extends TelemetryResponse {
    private CharacterResponse character;
    private String weaponId;
    private int fireCount;

    private LogWeaponFireCount(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.weaponId = (String) origin.get("weaponId");
        this.fireCount = (int) origin.get("fireCount");
    }

    public static LogWeaponFireCount mappedBy(Map<String, Object> origin) {
        return new LogWeaponFireCount(origin);
    }
}
