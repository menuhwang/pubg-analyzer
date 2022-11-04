package com.menu.pubganalyzer.controller;

import com.menu.pubganalyzer.service.MatchesService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {
    private final MatchesService matchesService;

    public MatchController(MatchesService matchesService) {
        this.matchesService = matchesService;
    }

    @GetMapping(value = "/matches/{matchId}", produces = "application/json; charset=UTF-8")
    public String getMatchInformation(@PathVariable String matchId) {
        return this.matchesService.getMatch(matchId).getInformation();
    }

    @GetMapping(value = "/matches/{matchId}/{playerName}", produces = "application/json; charset=UTF-8")
    public String getParticipantStat(@PathVariable String matchId, @PathVariable String playerName) {
        JSONObject toReturn = new JSONObject();
        try {
            toReturn.put("match", new JSONObject(this.matchesService.getMatch(matchId).getInformation()));
            toReturn.put("stats", new JSONObject(this.matchesService.getMatch(matchId).findParticipantByName(playerName)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return toReturn.toString();
    }
}
