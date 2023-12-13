package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogPlayerRevive extends TelemetryResponse {
    private CharacterResponse reviver;
    private CharacterResponse victim;
    private int dBNOId;

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
