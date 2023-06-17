package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.util.pubgAPI.PubgAPI;
import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.PlayersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FetchAPIService {
    private final PubgAPI pubgAPI;

    public PlayersResponse player(String shard, String nickname) {
        return pubgAPI.player(Shard.valueOf(shard).name(), List.of(nickname));
    }

    public MatchResponse match(String shard, String matchId) {
        return pubgAPI.match(Shard.valueOf(shard).name(), matchId);
    }
}
