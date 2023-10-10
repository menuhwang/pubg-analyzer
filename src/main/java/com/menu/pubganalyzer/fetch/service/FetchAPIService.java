package com.menu.pubganalyzer.fetch.service;

import com.menu.pubganalyzer.util.pubgAPI.response.match.MatchResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.PlayersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FetchAPIService {
    private final PubgService pubgService;

    public PlayersResponse player(String shard, String nickname) {
        return pubgService.fetchPlayer(shard, nickname);
    }

    public MatchResponse match(String shard, String matchId) {
        return pubgService.fetchMatch(shard, matchId);
    }
}
