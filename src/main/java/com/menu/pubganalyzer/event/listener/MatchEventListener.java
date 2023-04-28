package com.menu.pubganalyzer.event.listener;

import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.repository.MatchRepository;
import com.menu.pubganalyzer.event.SaveMatchesEvent;
import com.menu.pubganalyzer.event.SaveTelemetryEvent;
import com.menu.pubganalyzer.util.telemetryFileManager.TelemetryFileManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Slf4j
@Component
@RequiredArgsConstructor
public class MatchEventListener {
    private final MatchRepository matchRepository;
    private final TelemetryFileManager telemetryFileManager;

    @Async("sqlExecutor")
    @EventListener
    public void saveMatches(SaveMatchesEvent event) {
        Collection<Match> matches = event.getMatches();
        log.info("매치 DB insert 시작 matches size:{}", matches.size());
        long start = System.currentTimeMillis();
        matchRepository.saveAll(matches);
        log.info("매치 DB insert 종료 matches size:{}, time:{}ms", matches.size(), System.currentTimeMillis() - start);
    }

    @Async("sqlExecutor")
    @EventListener
    public void saveTelemetry(SaveTelemetryEvent event) {
        log.info("텔레메트리 저장 시작");
        long start = System.currentTimeMillis();
        telemetryFileManager.save(event.getMatchId(), event.getLogPlayerKillsToList());
        telemetryFileManager.save(event.getMatchId(), event.getLogPlayerTakeDamagesToList());
        log.info("텔레메트리 저장 완료 time:{}ms", System.currentTimeMillis() - start);
    }
}
