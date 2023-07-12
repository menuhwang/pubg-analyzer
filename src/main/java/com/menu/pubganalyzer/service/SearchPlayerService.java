package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.SearchPlayer;
import com.menu.pubganalyzer.domain.dao.MatchDAO;
import com.menu.pubganalyzer.domain.dao.ParticipantDAO;
import com.menu.pubganalyzer.domain.dao.PlayerDAO;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Participant;
import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.domain.model.PlayerMatch;
import com.menu.pubganalyzer.domain.repository.PlayerMatchRepository;
import com.menu.pubganalyzer.event.publisher.MatchEventPublisher;
import com.menu.pubganalyzer.event.publisher.PlayerEventPublisher;
import com.menu.pubganalyzer.exception.MatchNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.support.ExecutorServiceAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchPlayerService {
    private final PlayerDAO playerDAO;
    private final ParticipantDAO participantDAO;
    private final MatchDAO matchDAO;
    private final PlayerMatchRepository playerMatchRepository;
    private final PlayerEventPublisher playerEventPublisher;
    private final MatchEventPublisher matchEventPublisher;
    private final TaskExecutor pubgApiExecutor;

    @Transactional
    public SearchPlayer searchPlayer(String nickname, Pageable pageable) {
        Player player = playerDAO.findByNickname(nickname);

        Page<PlayerMatch> playerMatches = playerMatchRepository.findByPlayer(player, pageable);
        final String playerName = player.getName();

        Page<Participant> participants = playerMatches.map(playerMatch -> participantDAO.findByMatchIdAndPlayerName(playerMatch.getMatchId(), playerName));

        return SearchPlayer.of(player, participants);
    }

    public void updateMatchHistory(String nickname) {
        Player player = playerDAO.fetch(nickname);

        // 매치 병렬 조회 - 시작
        List<Future<Match>> matchFutures = new ArrayList<>(player.getMatchIds().size());

        ExecutorService executorService = new ExecutorServiceAdapter(pubgApiExecutor);
        for (String matchId : player.getMatchIds()) {
            Future<Match> matchFuture = executorService.submit(() -> {
                try {
                    return matchDAO.findById(matchId);
                } catch (MatchNotFoundException e) {
                    log.warn("[{}] {} match id:{}", e.getClass().getName(), e.getMessage(), e.getMatchId());
                    return null;
                }
            });

            matchFutures.add(matchFuture);
        }

        List<Match> matches = new ArrayList<>();
        String transactionId = MDC.get("transactionId");
        for (Future<Match> matchFuture : matchFutures) {
            try {
                Match match = matchFuture.get(1000L, TimeUnit.MILLISECONDS);
                matches.add(match);
            } catch (InterruptedException | ExecutionException e) {
                log.warn("[{}] 전적 갱신 실패", transactionId);
            } catch (TimeoutException e) {
                log.warn("[{}] 전적 갱신 시간 초과", transactionId);
            }
        }
        // 매치 병렬 조회 - 끝

        for (Match match : matches) player.addMatch(match.getId(), match.getCreatedAt());

        if (matches.get(0).getShardId() != player.getShardId()) {
            player.updateShard(matches.get(0).getShardId());
            playerDAO.save(player);
        }

        playerEventPublisher.updateMatchHistory(player);
        matchEventPublisher.saveMatches(matches);
    }
}
