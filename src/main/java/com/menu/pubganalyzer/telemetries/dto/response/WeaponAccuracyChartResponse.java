package com.menu.pubganalyzer.telemetries.dto.response;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class WeaponAccuracyChartResponse {
    private final List<String> labels;
    private final List<BarChartDataset<Integer>> datasets;

    public WeaponAccuracyChartResponse(List<String> labels, List<BarChartDataset<Integer>> datasets) {
        this.labels = labels;
        this.datasets = datasets;
    }
}
