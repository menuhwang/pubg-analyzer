package com.menu.pubganalyzer.util.telemetryFileManager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DefaultTelemetryFileManagerTest {
    private static final ResourceLoader resourceLoader = new DefaultResourceLoader();
    private static String FOLDER;
    private static String PATH;
    private static TelemetryFileManager telemetryFileManager;

    @BeforeAll
    static void beforeAll() throws IOException {
        FOLDER = "data";
        PATH = resourceLoader.getResource("file:").getFile().getAbsolutePath();
        telemetryFileManager = new DefaultTelemetryFileManager(PATH);
    }

    @Order(1)
    @Test
    void save() {
        List<Integer> data = List.of(1, 2, 3, 4, 5);
        telemetryFileManager.save(FOLDER, data);
        File file = new File(PATH + File.separator + FOLDER + File.separator + data.get(0).getClass().getSimpleName());
        assertTrue(file.exists());
    }

    @Order(2)
    @Test
    void exists() {
        assertTrue(telemetryFileManager.exists(FOLDER, Integer.class));
    }

    @Order(3)
    @Test
    void read() {
        Optional<List<Integer>> optionalData = telemetryFileManager.read(FOLDER, Integer.class);

        assertTrue(optionalData.isPresent());

        List<Integer> data = optionalData.get();

        for (int i : data) System.out.println(i);
        for (int i = 1; i <= 5; i++) assertTrue(data.contains(i));
    }
}