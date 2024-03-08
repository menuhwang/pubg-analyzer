package com.menu.pubganalyzer.util.chartjs;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class Bar {
    private final List<String> labels;
    private final List<Dataset> datasets;

    public Bar(List<String> labels, List<Dataset> datasets) {
        this.labels = labels;
        this.datasets = datasets;
    }
}
