package com.menu.pubganalyzer.telemetries.model;

import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Getter
@AllArgsConstructor
@Document(collection = "telemetries")
public class TelemetryEntity {
    @Id
    private final String id;
    private final String matchId;
    private final TelemetryResponse telemetry;

    public static TelemetryEntity of(String matchId, TelemetryResponse telemetryResponse) {
        return new TelemetryEntity(null, matchId, telemetryResponse);
    }
}
