package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogWeaponFireCount extends TelemetryResponse {
    private final CharacterResponse character;
    private final String weaponId;
    private final int fireCount;

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
