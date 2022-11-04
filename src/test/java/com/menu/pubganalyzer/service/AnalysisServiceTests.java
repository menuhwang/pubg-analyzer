package com.menu.pubganalyzer.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnalysisServiceTests {
    @Autowired
    private AnalysisService analysisService;

    @Test
    public void analyzeDamage() {
        String matchId = "7d205f42-3a04-4687-b894-60a9725361d8";
        String[] playerNames = {"Patriot_Engineer", "LSWW1", "ss_barl", "Mr_POLOO"};
        System.out.println(this.analysisService.killAndDamage(matchId, playerNames));
    }
}
