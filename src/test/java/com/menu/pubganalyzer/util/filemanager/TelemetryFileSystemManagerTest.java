package com.menu.pubganalyzer.util.filemanager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.menu.pubganalyzer.domain.model.telemetries.Telemetry;
import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;
import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TelemetryFileSystemManagerTest {
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private final String TEST_RESOURCE_TELEMETRIES_DIR = Objects.requireNonNull(this.getClass().getResource("/telemetries")).getFile();

    private final FileManager telemetryFileSystemManager = FileManagerFactory.getFileSystemManager(TEST_RESOURCE_TELEMETRIES_DIR);

    @Test
    @Order(1)
    @DisplayName("Telemetry 엔티티 json 형식으로 저장")
    void saveAll() {
        File telemetryResponseDir = new File(TEST_RESOURCE_TELEMETRIES_DIR, File.separator + "response");
        assertTrue(telemetryResponseDir.isDirectory());
        File[] telemetryFiles = telemetryResponseDir.listFiles();
        assertNotNull(telemetryFiles);
        StringBuilder sb;
        for (File telemetryFile : telemetryFiles) {
            String filename = telemetryFile.getName();
            String matchId = filename.substring(0, filename.lastIndexOf('.'));
            sb = new StringBuilder();
            try (
                    FileReader fr = new FileReader(telemetryFile);
                    BufferedReader br = new BufferedReader(fr);
            ) {
                br.lines().forEach(sb::append);
                List<TelemetryResponse> telemetryResponses = objectMapper.readValue(sb.toString(), new TypeReference<List<TelemetryResponse>>() {});
                List<Telemetry> result = telemetryResponses.stream()
                        .map(Telemetry::from)
                        .collect(Collectors.toList());

                String json = objectMapper.writeValueAsString(result);
                assertDoesNotThrow(() -> telemetryFileSystemManager.saveJson(matchId, json));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    @Order(2)
    @DisplayName("직렬화된 json 문자열로 읽기")
    void readJson() {
        File telemetryResponseDir = new File(TEST_RESOURCE_TELEMETRIES_DIR, File.separator + "response");
        assertTrue(telemetryResponseDir.isDirectory());
        File[] telemetryFiles = telemetryResponseDir.listFiles();
        assertNotNull(telemetryFiles);
        for (File telemetryFile : telemetryFiles) {
            String filename = telemetryFile.getName();
            String matchId = filename.substring(0, filename.lastIndexOf('.'));

            String json = telemetryFileSystemManager.readJson(matchId);

            assertNotNull(json);
            assertFalse(json.isBlank());
        }
    }
}