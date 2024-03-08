package com.menu.pubganalyzer.telemetries.dto.response;

import com.menu.pubganalyzer.util.chartjs.Bar;
import com.menu.pubganalyzer.util.chartjs.Dataset;
import lombok.Getter;

import java.util.List;

@Getter
public class WeaponAccuracyChartResponse extends Bar {
    public WeaponAccuracyChartResponse(List<String> labels, List<Dataset> datasets) {
        super(labels, datasets);
    }
}
