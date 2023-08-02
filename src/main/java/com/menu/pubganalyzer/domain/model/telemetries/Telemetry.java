package com.menu.pubganalyzer.domain.model.telemetries;

import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Telemetry {
    private String type;
    private LocalDateTime timestamp;
    private Common common;
    private Map<String, Object> attribute;

    public static Telemetry from(TelemetryResponse telemetry) {
        return Telemetry.builder()
                .type(telemetry.getType())
                .timestamp(telemetry.getTimestamp())
                .common(Common.from(telemetry.getCommon()))
                .attribute(telemetry.getAttribute())
                .build();
    }
}
