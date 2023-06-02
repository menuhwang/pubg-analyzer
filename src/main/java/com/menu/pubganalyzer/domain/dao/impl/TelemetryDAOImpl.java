package com.menu.pubganalyzer.domain.dao.impl;

import com.menu.pubganalyzer.config.CacheType;
import com.menu.pubganalyzer.domain.Telemetry;
import com.menu.pubganalyzer.domain.dao.TelemetryDAO;
import com.menu.pubganalyzer.domain.model.LogPlayerKillV2;
import com.menu.pubganalyzer.domain.model.LogPlayerTakeDamage;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Roster;
import com.menu.pubganalyzer.domain.repository.LogPlayerKillV2Repository;
import com.menu.pubganalyzer.domain.repository.LogPlayerTakeDamageRepository;
import com.menu.pubganalyzer.event.publisher.TelemetryEventPublisher;
import com.menu.pubganalyzer.util.pubgAPI.PubgAPI;
import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.cache.Cache.ValueWrapper;

@Component
public class TelemetryDAOImpl implements TelemetryDAO {
    private final LogPlayerKillV2Repository logPlayerKillV2Repository;
    private final LogPlayerTakeDamageRepository logPlayerTakeDamageRepository;
    private final PubgAPI pubgAPI;
    private final TelemetryEventPublisher telemetryEventPublisher;
    private final Cache telemetryCache;
    private final Cache rosterTelemetryCache;

    public TelemetryDAOImpl(
            LogPlayerKillV2Repository logPlayerKillV2Repository,
            LogPlayerTakeDamageRepository logPlayerTakeDamageRepository,
            PubgAPI pubgAPI,
            TelemetryEventPublisher telemetryEventPublisher,
            CacheManager cacheManager) {
        this.logPlayerKillV2Repository = logPlayerKillV2Repository;
        this.logPlayerTakeDamageRepository = logPlayerTakeDamageRepository;
        this.pubgAPI = pubgAPI;
        this.telemetryEventPublisher = telemetryEventPublisher;
        this.telemetryCache = cacheManager.getCache(CacheType.TELEMETRY.getCacheName());
        this.rosterTelemetryCache = cacheManager.getCache(CacheType.ROSTER_TELEMETRY.getCacheName());
    }

    @Override
    public Telemetry findByMatchId(Match match) {
        Telemetry telemetry = telemetryCache.get(match.getId(), Telemetry.class);
        if (telemetry != null) return telemetry;

        if (existsTelemetry(match.getId())) {
            List<LogPlayerKillV2> logPlayerKills = logPlayerKillV2Repository.findByMatchIdOrderByTimestamp(match.getId());
            List<LogPlayerTakeDamage> logPlayerTakeDamages = logPlayerTakeDamageRepository.findByMatchIdOrderByTimestamp(match.getId());
            telemetry = Telemetry.builder()
                    .logPlayerKills(logPlayerKills)
                    .logPlayerTakeDamages(logPlayerTakeDamages)
                    .build();
            telemetryCache.put(match.getId(), telemetry);
        } else {
            telemetry = fetchTelemetry(match);
        }

        return telemetry;
    }

    @Override
    public Telemetry findByMatchAndRoster(Match match, Roster roster) {
        String key = match.getId() + "_" + roster.getId();
        Telemetry rosterTelemetry = rosterTelemetryCache.get(key, Telemetry.class);
        if (rosterTelemetry != null) return rosterTelemetry;

        Telemetry telemetry = findByMatchId(match);

        rosterTelemetry = Telemetry.ofRoster(telemetry, roster);

        rosterTelemetryCache.put(key, rosterTelemetry);

        return rosterTelemetry;
    }

    private Telemetry fetchTelemetry(Match match) {
        List<TelemetryResponse> telemetryResponses = pubgAPI.telemetry(match.getAsset().getUrl());
        Telemetry telemetry = Telemetry.of(telemetryResponses, match.getId());
        ValueWrapper value = telemetryCache.putIfAbsent(match.getId(), telemetry);
        // value가 null이면 새로 값이 추가된 것을 의미.
        // value가 null이 아니면 이미 값이 있음을 의미.
        if (value == null) telemetryEventPublisher.saveTelemetry(telemetry);
        return telemetry;
    }

    private boolean existsTelemetry(String matchId) {
        return logPlayerKillV2Repository.existsByMatchId(matchId)
                && logPlayerTakeDamageRepository.existsByMatchId(matchId);
    }
}
