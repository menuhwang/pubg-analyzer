package com.menu.pubganalyzer.support.fixture.pubgapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.menu.pubganalyzer.support.fixture.pubgapi.MatchResponseFixture.MATCH_ID;

public class TelemetryResponseFixture {
    public static final List<TelemetryResponse> OFFICIAL_TELEMETRY_RESPONSE;

    static {
        try {
            ClassPathResource classpath = new ClassPathResource("telemetries" + File.separator + "response");
            File responseDir = classpath.getFile();

            OFFICIAL_TELEMETRY_RESPONSE = read(new File(responseDir, MATCH_ID + ".json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<TelemetryResponse> read(File file) {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        List<TelemetryResponse> result = Collections.emptyList();
        try {
            result = objectMapper.readValue(file, new TypeReference<List<TelemetryResponse>>() {});
        } catch (IOException e) {
            throw new RuntimeException();
        }

        return result;
    }
}
