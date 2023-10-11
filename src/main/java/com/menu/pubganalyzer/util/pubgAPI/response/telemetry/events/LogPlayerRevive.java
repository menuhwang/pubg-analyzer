package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogPlayerRevive extends TelemetryResponse {
    private final CharacterResponse reviver;
    private final CharacterResponse victim;
    private final int dBNOId;

    private LogPlayerRevive(Map<String, Object> origin) {
        super(origin);
        this.reviver = CharacterResponse.mappedBy(origin.get("reviver"));
        this.victim = CharacterResponse.mappedBy(origin.get("victim"));
        this.dBNOId = (int) origin.get("dBNOId");
    }

    public static LogPlayerRevive mappedBy(Map<String, Object> origin) {
        return new LogPlayerRevive(origin);
    }
}
