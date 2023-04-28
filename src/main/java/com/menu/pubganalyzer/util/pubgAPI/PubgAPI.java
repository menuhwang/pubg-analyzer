package com.menu.pubganalyzer.util.pubgAPI;

import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.PlayersResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;

import java.util.Collection;
import java.util.List;

public interface PubgAPI {
    void setShard(Shard shard);

    MatchResponse match(String matchId);

    PlayersResponse player(Collection<String> nicknames);

    List<TelemetryResponse> telemetry(String url);
}
