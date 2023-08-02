package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.domain.model.matches.Match;
import com.menu.pubganalyzer.domain.model.telemetries.Telemetry;
import com.menu.pubganalyzer.util.pubgAPI.PubgAPI;
import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.PlayersResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;
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
import java.util.stream.Collectors;

import static com.menu.pubganalyzer.domain.model.enums.Shard.STEAM;

@Service
@RequiredArgsConstructor
@Slf4j
public class PubgService {
    private final PubgAPI pubgAPI;
    private final TaskExecutor pubgApiExecutor;

    public PlayersResponse fetchPlayer(String shard, String nickname) {
        return pubgAPI.player(Shard.valueOf(shard).name(), nickname);
    }

    public MatchResponse fetchMatch(String shard, String matchId) {
        return pubgAPI.match(Shard.valueOf(shard).name(), matchId);
    }

    public Player fetchPlayer(String nickname) {
        PlayersResponse playersResponse = pubgAPI.player(STEAM.name(), nickname);
        return Player.of(playersResponse).get(0);
    }

    public List<Match> fetchMatches(Collection<String> matchIds) {
        if (matchIds.isEmpty()) return Collections.emptyList();

        // 매치 병렬 조회 - 시작
        List<Future<Match>> matchFutures = new ArrayList<>(matchIds.size());

        ExecutorService executorService = new ExecutorServiceAdapter(pubgApiExecutor);
        for (String matchId : matchIds) {
            Future<Match> matchFuture = executorService.submit(() ->
                    Match.of(pubgAPI.match(STEAM.name(), matchId))
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

    public List<Telemetry> fetchTelemetry(Match match) {
        String telemetryUrl = match.getTelemetryUrl();
        List<TelemetryResponse> telemetryResponses = pubgAPI.telemetry(telemetryUrl);

        return telemetryResponses.stream()
                .map(Telemetry::from)
                .collect(Collectors.toList());
    }
}
