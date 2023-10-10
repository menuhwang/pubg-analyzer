package com.menu.pubganalyzer.util.pubgAPI;

import com.menu.pubganalyzer.util.pubgAPI.response.match.MatchResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.player.PlayersResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;

import java.util.List;

public interface PubgAPI {
    MatchResponse match(String shardId, String matchId);
    PlayersResponse player(String shardId, String nickname);
    List<TelemetryResponse> telemetry(String url);
}
