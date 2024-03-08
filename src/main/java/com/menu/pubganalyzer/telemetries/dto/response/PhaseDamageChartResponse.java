package com.menu.pubganalyzer.telemetries.dto.response;

import com.menu.pubganalyzer.util.chartjs.Dataset;
import com.menu.pubganalyzer.util.chartjs.Doughnut;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class PhaseDamageChartResponse extends Doughnut {
    public PhaseDamageChartResponse(List<String> labels, List<Dataset> datasets) {
        super(labels, datasets);
    }
}
