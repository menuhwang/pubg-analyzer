package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogCarePackageSpawn extends TelemetryResponse {
    private final ItemPackageResponse itemPackage;

    private LogCarePackageSpawn(Map<String, Object> origin) {
        super(origin);
        this.itemPackage = ItemPackageResponse.mappedBy(origin.get("itemPackage"));
    }

    public static LogCarePackageSpawn mappedBy(Map<String, Object> origin) {
        return new LogCarePackageSpawn(origin);
    }
}
