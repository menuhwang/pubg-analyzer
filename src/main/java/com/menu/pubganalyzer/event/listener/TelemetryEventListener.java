package com.menu.pubganalyzer.event.listener;

import com.menu.pubganalyzer.domain.model.LogPlayerKillV2;
import com.menu.pubganalyzer.domain.model.LogPlayerTakeDamage;
import com.menu.pubganalyzer.domain.repository.LogPlayerKillV2Repository;
import com.menu.pubganalyzer.domain.repository.LogPlayerTakeDamageRepository;
import com.menu.pubganalyzer.event.InsertTelemetryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelemetryEventListener {
    private final LogPlayerKillV2Repository logPlayerKillV2Repository;
    private final LogPlayerTakeDamageRepository logPlayerTakeDamageRepository;

    @Async("sqlExecutor")
    @EventListener
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void saveTelemetry(InsertTelemetryEvent event) {
        List<LogPlayerKillV2> logPlayerKills = event.getLogPlayerKill();
        List<LogPlayerTakeDamage> logPlayerTakeDamages = event.getLogPlayerTakeDamages();
        String matchId = logPlayerKills.get(0).getMatchId();

        if (logPlayerKillV2Repository.existsByMatchId(matchId) || logPlayerTakeDamageRepository.existsByMatchId(matchId)) {
            return;
        }

        log.info("Telemetry:LogPlayerKill DB insert 시작 size:{}", logPlayerKills.size());
        long start = System.currentTimeMillis();
        logPlayerKillV2Repository.saveAll(logPlayerKills);
        log.info("Telemetry:LogPlayerKill DB insert 종료 size:{}, time:{}ms", logPlayerKills.size(), System.currentTimeMillis() - start);

        log.info("Telemetry:LogPlayerTakeDamage DB insert 시작 size:{}", logPlayerTakeDamages.size());
        start = System.currentTimeMillis();
        logPlayerTakeDamageRepository.saveAll(logPlayerTakeDamages);
        log.info("Telemetry:LogPlayerTakeDamage DB insert 종료 size:{}, time:{}ms", logPlayerTakeDamages.size(), System.currentTimeMillis() - start);
    }
}