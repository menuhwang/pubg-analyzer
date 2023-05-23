package com.menu.pubganalyzer.event.listener;

import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.repository.MatchRepository;
import com.menu.pubganalyzer.event.SaveMatchesEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class MatchEventListener {
    private final MatchRepository matchRepository;

    @Async("sqlExecutor")
    @EventListener
    public void saveMatches(SaveMatchesEvent event) {
        Set<Match> matches = new HashSet<>(event.getMatches());

        List<Match> exists = matchRepository.findAllById(Match.extractIds(matches));
        exists.forEach(matches::remove);

        log.info("매치 DB insert 시작 matches size:{}", matches.size());
        long start = System.currentTimeMillis();
        matchRepository.saveAll(matches);
        log.info("매치 DB insert 종료 matches size:{}, time:{}ms", matches.size(), System.currentTimeMillis() - start);
    }
}
