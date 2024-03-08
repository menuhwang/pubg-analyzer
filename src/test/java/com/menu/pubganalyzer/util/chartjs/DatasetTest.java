package com.menu.pubganalyzer.util.chartjs;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatasetTest {

    @Test
    void createInstance() {
        String label = "My First Dataset";
        List<Integer> data = List.of(65, 59, 80, 81, 56, 55, 40);

        Dataset dataset = assertDoesNotThrow(() -> new Dataset(label, data));

        assertEquals(label, dataset.getLabel());
        assertEquals(data, dataset.getData());
    }
}