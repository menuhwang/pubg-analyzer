package com.menu.pubganalyzer.event.listener;

import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.repository.MatchRepository;
import com.menu.pubganalyzer.event.SaveMatchesEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class MatchEventListener {
    private final MatchRepository matchRepository;

    @Async("sqlExecutor")
    @EventListener
    @Transactional
    public void saveMatches(SaveMatchesEvent event) {
        Set<Match> matches = new HashSet<>(event.getMatches());

        matches.removeIf(match -> matchRepository.findByIdShardLock(match.getId()).isPresent());

        if (matches.isEmpty()) return;

        String transactionId = event.getTransactionId();
        log.info("[{}] 매치 DB insert 시작 matches size:{}", transactionId, matches.size());
        long start = System.currentTimeMillis();
        for (Match match : matches) {
            matchRepository.saveAndFlush(match);
        }
        log.info("[{}] 매치 DB insert 종료 time:{}ms", transactionId, System.currentTimeMillis() - start);
    }
}
