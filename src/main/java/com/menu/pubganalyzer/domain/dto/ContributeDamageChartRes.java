package com.menu.pubganalyzer.domain.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class ContributeDamageChartRes {
    private final List<String> labels;
    private final List<ContributeDamageChartDataset> datasets;

    public ContributeDamageChartRes(List<String> labels, List<ContributeDamageChartDataset> datasets) {
        this.labels = labels;
        this.datasets = datasets;
    }
}
