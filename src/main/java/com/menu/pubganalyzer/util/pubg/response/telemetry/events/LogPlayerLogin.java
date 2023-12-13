package com.menu.pubganalyzer.util.pubg.response.telemetry.events;

import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogPlayerLogin extends TelemetryResponse {
    private String accountId;

    private LogPlayerLogin(Map<String, Object> origin) {
        super(origin);
        this.accountId = (String) origin.get("accountId");
    }

    public static LogPlayerLogin mappedBy(Map<String, Object> origin) {
        return new LogPlayerLogin(origin);
    }
}
