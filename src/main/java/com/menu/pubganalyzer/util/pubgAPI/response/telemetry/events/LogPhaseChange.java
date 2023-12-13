package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogPhaseChange extends TelemetryResponse {
    private int phase;
    private double elapsedTime;

    private LogPhaseChange(Map<String, Object> origin) {
        super(origin);
        this.phase = (int) origin.get("phase");
        this.elapsedTime = ((Number) origin.getOrDefault("elapsedTime", 0D)).doubleValue();
    }

    public static LogPhaseChange mappedBy(Map<String, Object> origin) {
        return new LogPhaseChange(origin);
    }
}
