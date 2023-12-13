package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogCarePackageSpawn extends TelemetryResponse {
    private ItemPackageResponse itemPackage;

    private LogCarePackageSpawn(Map<String, Object> origin) {
        super(origin);
        this.itemPackage = ItemPackageResponse.mappedBy(origin.get("itemPackage"));
    }

    public static LogCarePackageSpawn mappedBy(Map<String, Object> origin) {
        return new LogCarePackageSpawn(origin);
    }
}
