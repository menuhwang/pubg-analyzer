package com.menu.pubganalyzer.telemetries.dto.response;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class ContributeDamageChartResponse {
    private final List<String> labels;
    private final List<BarChartDataset<Float>> datasets;

    public ContributeDamageChartResponse(List<String> labels, List<BarChartDataset<Float>> datasets) {
        this.labels = labels;
        this.datasets = datasets;
    }
}
