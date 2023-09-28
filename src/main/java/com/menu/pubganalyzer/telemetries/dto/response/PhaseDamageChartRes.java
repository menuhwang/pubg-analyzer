package com.menu.pubganalyzer.telemetries.dto.response;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PhaseDamageChartRes {
    private final float[] data;

    public PhaseDamageChartRes(float[] data) {
        this.data = data;
    }
}
