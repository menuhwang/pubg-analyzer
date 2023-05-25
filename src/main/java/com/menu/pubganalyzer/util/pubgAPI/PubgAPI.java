package com.menu.pubganalyzer.util.pubgAPI;

import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.PlayersResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;

import java.util.Collection;
import java.util.List;

public interface PubgAPI {

    MatchResponse match(String shardId, String matchId);

    PlayersResponse player(String shardId, Collection<String> nicknames);

    List<TelemetryResponse> telemetry(String url);
}
