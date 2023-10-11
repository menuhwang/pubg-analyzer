package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogPlayerLogin extends TelemetryResponse {
    private final String accountId;

    private LogPlayerLogin(Map<String, Object> origin) {
        super(origin);
        this.accountId = (String) origin.get("accountId");
    }

    public static LogPlayerLogin mappedBy(Map<String, Object> origin) {
        return new LogPlayerLogin(origin);
    }
}
