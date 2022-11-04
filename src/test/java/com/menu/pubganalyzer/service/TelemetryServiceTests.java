package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.lib.Telemetry.Telemetry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TelemetryServiceTests {
    @Autowired
    private TelemetryService telemetryService;
    private String matchId = "eaa616f2-f6d9-48ae-958c-64e77443aa86";

    @Test
    public void getTelemetry() {
        Telemetry telemetry = telemetryService.getTelemetry(matchId);
        System.out.println("LogPlayerKill Line(s) : " + telemetry.getLogPlayerKillList().size());
        System.out.println("LogPlayerTakeDamage Line(s) : " + telemetry.getLogPlayerTakeDamageList().size());
    }
}
