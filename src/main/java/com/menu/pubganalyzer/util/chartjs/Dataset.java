package com.menu.pubganalyzer.util.chartjs;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class Dataset {
    private final String label;
    private final List<? extends Number> data;

    public Dataset(String label, List<? extends Number> data) {
        this.label = label;
        this.data = List.copyOf(data);
    }
}
