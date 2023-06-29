package com.menu.pubganalyzer.event.listener;

import com.menu.pubganalyzer.domain.model.LogPlayerKillV2;
import com.menu.pubganalyzer.domain.model.LogPlayerTakeDamage;
import com.menu.pubganalyzer.domain.repository.LogPlayerKillV2Repository;
import com.menu.pubganalyzer.domain.repository.LogPlayerTakeDamageRepository;
import com.menu.pubganalyzer.event.SaveTelemetryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelemetryEventListener {
    private final LogPlayerKillV2Repository logPlayerKillV2Repository;
    private final LogPlayerTakeDamageRepository logPlayerTakeDamageRepository;

    @Async("sqlExecutor")
    @EventListener
    public void saveTelemetry(SaveTelemetryEvent event) {
        List<LogPlayerKillV2> logPlayerKills = event.getLogPlayerKill();
        List<LogPlayerTakeDamage> logPlayerTakeDamages = event.getLogPlayerTakeDamages();

        String transactionId = event.getTransactionId();
        log.debug("[{}] Telemetry:LogPlayerKill DB insert 시작 size:{}", transactionId, logPlayerKills.size());
        long start = System.currentTimeMillis();
        logPlayerKillV2Repository.saveAll(logPlayerKills);
        log.debug("[{}] Telemetry:LogPlayerKill DB insert 종료 time:{}ms", transactionId, System.currentTimeMillis() - start);

        log.debug("[{}] Telemetry:LogPlayerTakeDamage DB insert 시작 size:{}", transactionId, logPlayerTakeDamages.size());
        start = System.currentTimeMillis();
        logPlayerTakeDamageRepository.saveAll(logPlayerTakeDamages);
        log.info("[{}] Telemetry:LogPlayerTakeDamage DB insert 종료 time:{}ms", transactionId, System.currentTimeMillis() - start);
    }
}
