package com.menu.pubganalyzer.util.chartjs;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BarTest {

    @Test
    void createInstance() {
        List<String> labels = List.of("January", "February", "March", "April");
        List<Dataset> datasets = new ArrayList<>();
        datasets.add(new Dataset("Dataset 1", List.of(10, 20, 30, 40)));
        datasets.add(new Dataset("Dataset 2", List.of(40, 30, 20, 10)));

        assertDoesNotThrow(() -> new Bar(labels, datasets));
    }
}