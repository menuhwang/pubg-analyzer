package com.menu.pubganalyzer.support.fixture.pubgapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private static List<TelemetryResponse> read(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        List<Map<String, Object>> raw = objectMapper.readValue(file, new TypeReference<>() {});
        return raw.stream()
                .map(TelemetryResponse::from)
                .collect(Collectors.toList());
    }
}
