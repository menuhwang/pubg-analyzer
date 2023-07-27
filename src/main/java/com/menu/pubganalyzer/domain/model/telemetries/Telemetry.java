package com.menu.pubganalyzer.domain.model.telemetries;

import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
@Document(collection = "telemetries")
public class Telemetry {
    @Id
    private String id;
    @Indexed
    private String matchId;
    private String type;
    private LocalDateTime timestamp;
    private Common common;
    private Map<String, Object> attribute;

    public static Telemetry of(String matchId, TelemetryResponse telemetry) {
        return Telemetry.builder()
                .matchId(matchId)
                .type(telemetry.getType())
                .timestamp(telemetry.getTimestamp())
                .common(Common.from(telemetry.getCommon()))
                .attribute(telemetry.getAttribute())
                .build();
    }
}
