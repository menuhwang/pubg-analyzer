package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogPlayerLogout extends TelemetryResponse {
    private final String accountId;

    private LogPlayerLogout(Map<String, Object> origin) {
        super(origin);
        this.accountId = (String) origin.get("accountId");
    }

    public static LogPlayerLogout mappedBy(Map<String, Object> origin) {
        return new LogPlayerLogout(origin);
    }
}
