package com.menu.pubganalyzer.telemetries.dto.response;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class BarChartDataset<E> {
    private final String label;
    private final E[] data;

    public BarChartDataset(String label, E[] data) {
        this.label = label;
        this.data = data;
    }
}
