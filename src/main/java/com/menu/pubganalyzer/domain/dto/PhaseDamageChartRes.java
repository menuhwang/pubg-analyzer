package com.menu.pubganalyzer.domain.dto;

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
