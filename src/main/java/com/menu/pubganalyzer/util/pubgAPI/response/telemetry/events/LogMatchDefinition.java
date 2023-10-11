package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogMatchDefinition extends TelemetryResponse {
    private final String matchId;
    private final String seasonState;

    private LogMatchDefinition(Map<String, Object> origin) {
        super(origin);
        this.matchId = (String) origin.get("MatchId");
        this.seasonState = (String) origin.get("SeasonState");
    }

    public static LogMatchDefinition mappedBy(Map<String, Object> origin) {
        return new LogMatchDefinition(origin);
    }
}
