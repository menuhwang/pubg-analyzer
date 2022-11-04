package com.menu.pubganalyzer.lib.Telemetry;

import com.menu.pubganalyzer.service.TelemetryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TelemetryTests {
    private Telemetry telemetry;
    public TelemetryTests() {
        String matchId = "4fe52750-6894-4da4-8a95-75f09adf1603";
//        String matchId = "5e880991-0080-4f25-ac19-27722fb2eda5";
        this.telemetry = new TelemetryService().getTelemetry(matchId);
    }
    @Test
    public void getTest() {
        System.out.println("LogPlayerKillList size : " + this.telemetry.getLogPlayerKillList().size());
        System.out.println("LogPlayerTakeDamageList size : " + this.telemetry.getLogPlayerTakeDamageList().size());
    }
}
