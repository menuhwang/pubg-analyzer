package com.menu.pubganalyzer.controller;

import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.service.MatchesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {
    private final MatchesService matchesService;

    public MatchController(MatchesService matchesService) {
        this.matchesService = matchesService;
    }

    @GetMapping("/shard/{shardId}/matches/{matchId}")
    public Match getMatchInformation(@PathVariable String shardId, @PathVariable String matchId) {
        Shard shard = Shard.valueOf(shardId.toUpperCase());
        return this.matchesService.getMatch(shard, matchId);
    }
}
