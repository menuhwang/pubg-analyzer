package com.menu.pubganalyzer.util.pubg;

import com.menu.pubganalyzer.util.pubg.response.match.MatchResponse;
import com.menu.pubganalyzer.util.pubg.response.player.PlayersResponse;
import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;

import java.util.List;

public interface PubgClient {
    MatchResponse match(String shardId, String matchId);
    PlayersResponse player(String shardId, String nickname);
    List<TelemetryResponse> telemetry(String url);
}
