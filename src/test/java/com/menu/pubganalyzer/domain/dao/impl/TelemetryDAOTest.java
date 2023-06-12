package com.menu.pubganalyzer.domain.dao.impl;

import com.menu.pubganalyzer.domain.Telemetry;
import com.menu.pubganalyzer.domain.dao.TelemetryDAO;
import com.menu.pubganalyzer.domain.model.LogPlayerKillV2;
import com.menu.pubganalyzer.domain.model.LogPlayerTakeDamage;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Roster;
import com.menu.pubganalyzer.domain.repository.LogPlayerKillV2Repository;
import com.menu.pubganalyzer.domain.repository.LogPlayerTakeDamageRepository;
import com.menu.pubganalyzer.event.publisher.TelemetryEventPublisher;
import com.menu.pubganalyzer.support.fixture.MatchFixture;
import com.menu.pubganalyzer.support.fixture.TelemetryFixture;
import com.menu.pubganalyzer.support.fixture.pubgapi.TelemetryResponseFixture;
import com.menu.pubganalyzer.util.pubgAPI.PubgAPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.cache.Cache;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class TelemetryDAOTest {
    private final LogPlayerKillV2Repository logPlayerKillV2Repository = Mockito.mock(LogPlayerKillV2Repository.class);
    private final LogPlayerTakeDamageRepository logPlayerTakeDamageRepository = Mockito.mock(LogPlayerTakeDamageRepository.class);
    private final PubgAPI pubgAPI = Mockito.mock(PubgAPI.class);
    private final TelemetryEventPublisher telemetryEventPublisher = Mockito.mock(TelemetryEventPublisher.class);
    private final Cache telemetryCache = Mockito.mock(Cache.class);
    private final Cache rosterTelemetryCache = Mockito.mock(Cache.class);

    private final TelemetryDAO telemetryDAO = new TelemetryDAOImpl(
            logPlayerKillV2Repository,
            logPlayerTakeDamageRepository,
            pubgAPI,
            telemetryEventPublisher,
            telemetryCache,
            rosterTelemetryCache
    );

    private final Match MATCH = MatchFixture.MATCH;
    private final String MATCH_ID = MATCH.getId();
    private final Telemetry TELEMETRY = TelemetryFixture.TELEMETRY;
    private final List<LogPlayerKillV2> logPlayerKills = List.of(TelemetryFixture.LOG_PLAYER_KILL);
    private final List<LogPlayerTakeDamage> logPlayerTakeDamages = List.of(TelemetryFixture.LOG_PLAYER_TAKE_DAMAGE);
    private final Roster ROSTER = MatchFixture.ROSTER;
    private final String ROSTER_ID = ROSTER.getId();

    private final String ROSTER_TELEMETRY_KEY = MATCH_ID + "_" + ROSTER_ID;

    @Test
    @DisplayName("id 조회 Telemetry 캐시 히트 : Telemetry 캐시에서 반환한다.")
    void findByMatchId_fetch_telemetry_cache() {
        given(telemetryCache.get(MATCH_ID, Telemetry.class))
                .willReturn(TELEMETRY);

        Telemetry result = telemetryDAO.findByMatchId(MATCH);

        assertEquals(TELEMETRY, result);
        verify(telemetryCache).get(anyString(), ArgumentMatchers.<Class<Telemetry>>any());
        verify(logPlayerKillV2Repository, never()).existsByMatchId(anyString());
        verify(logPlayerTakeDamageRepository, never()).existsByMatchId(anyString());
        verify(logPlayerKillV2Repository, never()).findByMatchIdOrderByTimestamp(anyString());
        verify(logPlayerTakeDamageRepository, never()).findByMatchIdOrderByTimestamp(anyString());
        verify(telemetryCache, never()).put(anyString(), any(Telemetry.class));
    }

    @Test
    @DisplayName("id 조회 Telemetry 캐시 미스 : DB 검색, 캐시에 저장 후 반환한다.")
    void findByMatchId_fetch_telemetry_repository() {
        given(telemetryCache.get(MATCH_ID, Telemetry.class))
                .willReturn(null);
        given(logPlayerKillV2Repository.existsByMatchId(MATCH_ID))
                .willReturn(true);
        given(logPlayerTakeDamageRepository.existsByMatchId(MATCH_ID))
                .willReturn(true);
        given(logPlayerKillV2Repository.findByMatchIdOrderByTimestamp(MATCH_ID))
                .willReturn(logPlayerKills);
        given(logPlayerTakeDamageRepository.findByMatchIdOrderByTimestamp(MATCH_ID))
                .willReturn(logPlayerTakeDamages);

        telemetryDAO.findByMatchId(MATCH);

        verify(telemetryCache).get(anyString(), ArgumentMatchers.<Class<Telemetry>>any());
        verify(logPlayerKillV2Repository).existsByMatchId(anyString());
        verify(logPlayerTakeDamageRepository).existsByMatchId(anyString());
        verify(logPlayerKillV2Repository).findByMatchIdOrderByTimestamp(anyString());
        verify(logPlayerTakeDamageRepository).findByMatchIdOrderByTimestamp(anyString());
        verify(telemetryCache).put(anyString(), any(Telemetry.class));
    }

    @Test
    @DisplayName("id 조회 Telemetry 캐시 미스, DB not found : api 검색, 캐시에 저장 후 반환한다.")
    void findByMatchId_fetch_telemetry_api() {
        given(telemetryCache.get(MATCH_ID, Telemetry.class))
                .willReturn(null);
        given(logPlayerKillV2Repository.existsByMatchId(MATCH_ID))
                .willReturn(false);
        given(logPlayerTakeDamageRepository.existsByMatchId(MATCH_ID))
                .willReturn(false);
        given(pubgAPI.telemetry(MATCH.getAsset().getUrl()))
                .willReturn(TelemetryResponseFixture.TELEMETRY_RESPONSES);
        given(telemetryCache.putIfAbsent(eq(MATCH_ID), any(Telemetry.class)))
                .willReturn(null);

        telemetryDAO.findByMatchId(MATCH);

        verify(telemetryCache).get(anyString(), ArgumentMatchers.<Class<Telemetry>>any());
        verify(logPlayerKillV2Repository).existsByMatchId(anyString());
        verify(logPlayerTakeDamageRepository, never()).existsByMatchId(anyString());
        verify(logPlayerKillV2Repository, never()).findByMatchIdOrderByTimestamp(anyString());
        verify(logPlayerTakeDamageRepository, never()).findByMatchIdOrderByTimestamp(anyString());
        verify(telemetryCache, never()).put(anyString(), any(Telemetry.class));
        verify(pubgAPI).telemetry(anyString());
        verify(telemetryCache).putIfAbsent(anyString(), any(Telemetry.class));
        verify(telemetryEventPublisher).saveTelemetry(any(Telemetry.class));
    }

    @Test
    @DisplayName("매치와 로스터로 조회 RosterTelemetry 캐시 히트")
    void findByMatchAndRoster_fetch_rosterTelemetry_cache() {
        given(rosterTelemetryCache.get(ROSTER_TELEMETRY_KEY, Telemetry.class))
                .willReturn(TELEMETRY);

        Telemetry result = telemetryDAO.findByMatchAndRoster(MATCH, ROSTER);

        assertEquals(TELEMETRY, result);
        verify(rosterTelemetryCache).get(anyString(), ArgumentMatchers.<Class<Telemetry>>any());

        // findByMatchId(match)
        verify(telemetryCache, never()).get(anyString(), ArgumentMatchers.<Class<Telemetry>>any());
        verify(logPlayerKillV2Repository, never()).existsByMatchId(anyString());
        verify(logPlayerTakeDamageRepository, never()).existsByMatchId(anyString());
        verify(logPlayerKillV2Repository, never()).findByMatchIdOrderByTimestamp(anyString());
        verify(logPlayerTakeDamageRepository, never()).findByMatchIdOrderByTimestamp(anyString());
        verify(telemetryCache, never()).put(anyString(), any(Telemetry.class));
        verify(pubgAPI, never()).telemetry(anyString());
        verify(telemetryCache, never()).putIfAbsent(anyString(), any(Telemetry.class));
        verify(telemetryEventPublisher, never()).saveTelemetry(any(Telemetry.class));
        // findByMatchId(match)

        verify(rosterTelemetryCache, never()).put(anyString(), any(Telemetry.class));
    }

    @Test
    @DisplayName("매치와 로스터로 조회 RosterTelemetry 캐시 미스 : findByMatchId 실행")
    void findByMatchAndRoster_invoke_findByMatchId() {
        given(rosterTelemetryCache.get(ROSTER_TELEMETRY_KEY, Telemetry.class))
                .willReturn(null);

        findByMatchId_fetch_telemetry_cache();

        telemetryDAO.findByMatchAndRoster(MATCH, ROSTER);

        verify(rosterTelemetryCache).get(anyString(), ArgumentMatchers.<Class<Telemetry>>any());
        verify(rosterTelemetryCache).put(anyString(), any(Telemetry.class));
    }
}