package com.menu.pubganalyzer.domain.dto;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ContributeDamageChartDataset {
    private final String label;
    private final float[] data;

    public ContributeDamageChartDataset(String label, float[] data) {
        this.label = label;
        this.data = data;
    }
}
