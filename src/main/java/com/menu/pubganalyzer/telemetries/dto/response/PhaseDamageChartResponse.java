package com.menu.pubganalyzer.telemetries.dto.response;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PhaseDamageChartResponse {
    private final float[] data;

    public PhaseDamageChartResponse(float[] data) {
        this.data = data;
    }
}
