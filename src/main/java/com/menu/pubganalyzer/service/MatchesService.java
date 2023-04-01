package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.model.Match;
import com.menu.pubganalyzer.model.enums.Shard;
import com.menu.pubganalyzer.util.pubgAPI.PubgAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchesService {
    private final PubgAPI pubgAPI;

    public Match getMatch(@Nullable Shard shard, String matchId) {
        if (shard != null) pubgAPI.setShard(shard);
        Match match = pubgAPI.match(matchId);
        System.out.println(match);
        return match;
    }
}
