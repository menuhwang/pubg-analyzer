package com.menu.pubganalyzer.fetch.service;

import com.menu.pubganalyzer.players.model.Player;
import com.menu.pubganalyzer.common.enums.Shard;
import com.menu.pubganalyzer.matches.model.Match;
import com.menu.pubganalyzer.util.pubg.PubgClient;
import com.menu.pubganalyzer.util.pubg.response.match.MatchResponse;
import com.menu.pubganalyzer.util.pubg.response.player.PlayersResponse;
import com.menu.pubganalyzer.util.pubg.response.telemetry.TelemetryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.support.ExecutorServiceAdapter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

import static com.menu.pubganalyzer.common.enums.Shard.STEAM;

@Service
@RequiredArgsConstructor
@Slf4j
public class PubgService {
    private final PubgClient pubgClient;
    private final TaskExecutor pubgApiExecutor;

    public PlayersResponse fetchPlayer(String shard, String nickname) {
        return pubgClient.player(Shard.valueOf(shard).name(), nickname);
    }

    public MatchResponse fetchMatch(String shard, String matchId) {
        return pubgClient.match(Shard.valueOf(shard).name(), matchId);
    }

    public Player fetchPlayer(String nickname) {
        PlayersResponse playersResponse = pubgClient.player(STEAM.name().toLowerCase(), nickname);
        return Player.of(playersResponse).get(0);
    }

    public List<Match> fetchMatches(Collection<String> matchIds) {
        if (matchIds.isEmpty()) return Collections.emptyList();

        // 매치 병렬 조회 - 시작
        List<Future<Match>> matchFutures = new ArrayList<>(matchIds.size());

        ExecutorService executorService = new ExecutorServiceAdapter(pubgApiExecutor);
        for (String matchId : matchIds) {
            Future<Match> matchFuture = executorService.submit(() ->
                    Match.of(pubgClient.match(STEAM.name(), matchId))
            );

            matchFutures.add(matchFuture);
        }

        List<Match> matches = new ArrayList<>();
        String transactionId = MDC.get("transactionId");
        for (Future<Match> matchFuture : matchFutures) {
            try {
                Match match = matchFuture.get(2000L, TimeUnit.MILLISECONDS);
                matches.add(match);
            } catch (InterruptedException | ExecutionException e) {
                log.warn("[{}] 전적 갱신 실패", transactionId);
            } catch (TimeoutException e) {
                log.warn("[{}] 전적 갱신 시간 초과", transactionId);
            }
        }
        // 매치 병렬 조회 - 끝

        return matches;
    }

    public List<TelemetryResponse> fetchTelemetry(Match match) {
        String telemetryUrl = match.getTelemetryUrl();
        return pubgClient.telemetry(telemetryUrl);
    }
}
