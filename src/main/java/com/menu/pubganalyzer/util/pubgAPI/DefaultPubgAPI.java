package com.menu.pubganalyzer.util.pubgAPI;

import com.menu.pubganalyzer.util.pubg.MatchClient;
import com.menu.pubganalyzer.util.pubg.PlayerClient;
import com.menu.pubganalyzer.util.pubg.TelemetryClient;
import com.menu.pubganalyzer.util.pubgAPI.exception.PubgAPIPlayerNotFoundException;
import com.menu.pubganalyzer.util.pubgAPI.response.match.MatchResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.player.PlayersResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.menu.pubganalyzer.util.pubg.TelemetryClient.PUBG_TELEMETRY_CDN;

@Slf4j
@Component
public class DefaultPubgAPI implements PubgAPI {
    private final MatchClient matchClient;
    private final PlayerClient playerClient;
    private final TelemetryClient telemetryClient;

    public DefaultPubgAPI(MatchClient matchClient, PlayerClient playerClient, TelemetryClient telemetryClient) {
        this.matchClient = matchClient;
        this.playerClient = playerClient;
        this.telemetryClient = telemetryClient;
    }

    @Override
    @Cacheable(cacheNames = "match", key = "#matchId")
    public MatchResponse match(String shardId, String matchId) {
        return matchClient.fetchMatch(shardId, matchId);
    }

    @Override
    @Cacheable(cacheNames = "player", key = "#playerName")
    public PlayersResponse player(String shardId, String playerName) throws PubgAPIPlayerNotFoundException {
        return playerClient.fetchPlayers(shardId, playerName);
    }

    @Override
    @Cacheable(cacheNames = "telemetry")
    public List<TelemetryResponse> telemetry(String url) {
        if (!url.startsWith(PUBG_TELEMETRY_CDN))
            throw new IllegalArgumentException("telemetry url is wrong.");

        url = url.substring(PUBG_TELEMETRY_CDN.length());

        List<Object> raw = telemetryClient.fetchTelemetry(url);

        return raw.stream().map(TelemetryResponse::from).collect(Collectors.toList());
    }
}
