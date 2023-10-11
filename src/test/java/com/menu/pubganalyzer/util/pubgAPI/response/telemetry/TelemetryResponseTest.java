package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import com.menu.pubganalyzer.support.feign.FeignClientTest;
import com.menu.pubganalyzer.util.pubg.TelemetryClient;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@FeignClientTest
@ActiveProfiles("test")
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TelemetryResponseTest {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private TelemetryClient telemetryClient;
    private List<Object> telemetries;

    @Order(0)
    @Test
    void fetchTelemetry() {
        String path = "/bluehole-pubg/steam/2023/10/08/08/35/a28473f5-65b5-11ee-91b2-3205bf553122-telemetry.json";
        telemetries = telemetryClient.fetchTelemetry(path);
    }

    @Order(1)
    @Test
    void mapToTelemetryEvent() {
        List<TelemetryResponse> telemetryResponses = new ArrayList<>();

        for (Object telemetry : telemetries) {
            telemetryResponses.add(TelemetryResponse.from(telemetry));
        }

        log.info("telemetries size: {}", telemetryResponses.size());
    }
}