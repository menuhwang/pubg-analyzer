package com.menu.pubganalyzer.util.chartjs;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class Doughnut {
    private final List<String> labels;
    private final List<Dataset> datasets;

    public Doughnut(List<String> labels, List<Dataset> datasets) {
        this.labels = labels;
        this.datasets = datasets;
    }
}
