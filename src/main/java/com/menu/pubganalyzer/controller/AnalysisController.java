package com.menu.pubganalyzer.controller;

import com.menu.pubganalyzer.service.AnalysisService;
import com.menu.pubganalyzer.service.TelemetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalysisController {
    @Autowired
    private AnalysisService analysisService;
    @Autowired
    private TelemetryService telemetryService;

    @GetMapping(value = "/analysis/{matchId}/{playerNames}", produces = "application/json; charset=UTF-8")
    public String getAnalysisResult(@PathVariable String matchId, @PathVariable String playerNames) {
        String[] playerNameArray = playerNames.replace(" ", "").split(",");
        return this.analysisService.killAndDamage(matchId, playerNameArray);
    }

    @GetMapping(value = "/analysis/{matchId}/{playerName}/damages", produces = "application/json; charset=UTF-8")
    public String getPlayerDamages(@PathVariable String matchId, @PathVariable String playerName) {
        return this.telemetryService.getTelemetry(matchId).getLogPlayerTakeDamageList().getByAttacker(playerName).toJSON().toString();
    }
}
