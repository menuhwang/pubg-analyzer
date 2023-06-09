package com.menu.pubganalyzer.domain.dao.impl;

import com.menu.pubganalyzer.domain.dao.MatchDAO;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.repository.MatchRepository;
import com.menu.pubganalyzer.exception.MatchNotFoundException;
import com.menu.pubganalyzer.support.fixture.MatchFixture;
import com.menu.pubganalyzer.support.fixture.pubgapi.MatchResponseFixture;
import com.menu.pubganalyzer.util.pubgAPI.PubgAPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.cache.Cache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class MatchDAOTest {
    private final MatchRepository matchRepository = Mockito.mock(MatchRepository.class);
    private final PubgAPI pubgAPI = Mockito.mock(PubgAPI.class);
    private final Cache matchCache = Mockito.mock(Cache.class);
    private final Cache participantCache = Mockito.mock(Cache.class);

    private final MatchDAO matchDAO = new MatchDAOImpl(matchRepository, pubgAPI, matchCache, participantCache);

    private final String MATCH_ID = MatchFixture.MATCH_ID;
    private final Match MATCH = MatchFixture.MATCH;

    @Test
    @DisplayName("match id 조회 Match 캐시 히트 : Match 캐시에서 반환한다.")
    void findById_fetch_match_cache() {
        given(matchCache.get(MATCH_ID, Match.class))
                .willReturn(MATCH);

        Match result = matchDAO.findById(MATCH_ID);

        assertEquals(MATCH, result);
        verify(matchCache).get(anyString(), ArgumentMatchers.<Class<Match>>any());
        verify(matchRepository, never()).findByIdFetchParticipant(anyString());
        verify(pubgAPI, never()).match(anyString(), anyString());
        verify(matchCache, never()).put(anyString(), any(Match.class));
    }

    @Test
    @DisplayName("match id 조회 Match 캐시 미스 : DB에서 검색, 캐시에 저장 후 반환한다.")
    void findById_fetch_match_repository() {
        given(matchCache.get(MATCH_ID, Match.class))
                .willReturn(null);
        given(matchRepository.findByIdFetchParticipant(MATCH_ID))
                .willReturn(Optional.of(MATCH));

        Match result = matchDAO.findById(MATCH_ID);

        assertEquals(MATCH, result);
        verify(matchCache).get(anyString(), ArgumentMatchers.<Class<Match>>any());
        verify(matchRepository).findByIdFetchParticipant(anyString());
        verify(pubgAPI, never()).match(anyString(), anyString());
        verify(matchCache).put(anyString(), any(Match.class));
    }

    @Test
    @DisplayName("match id 조회 Match 캐시 미스, DB not found : api 요청, 캐시에 저장 후 반환한다.")
    void findById_fetch_match_api() {
        given(matchCache.get(MATCH_ID, Match.class))
                .willReturn(null);
        given(matchRepository.findByIdFetchParticipant(MATCH_ID))
                .willReturn(Optional.empty());
        given(pubgAPI.match(MatchFixture.MATCH_SHARD, MATCH_ID))
                .willReturn(MatchResponseFixture.MATCH_RESPONSE);

        Match result = matchDAO.findById(MATCH_ID);

        assertEquals(MATCH, result);
        verify(matchCache).get(anyString(), ArgumentMatchers.<Class<Match>>any());
        verify(matchRepository).findByIdFetchParticipant(anyString());
        verify(pubgAPI).match(anyString(), anyString());
        verify(matchCache).put(anyString(), any(Match.class));
    }

    @Test
    @DisplayName("매치 조회 페이징")
    void findById_paging() {
        given(matchRepository.findByIdContains(MATCH_ID, MatchFixture.PAGEABLE))
                .willReturn(MatchFixture.MATCH_PAGE);

        Page<Match> result = matchDAO.findById(MATCH_ID, MatchFixture.PAGEABLE);

        assertEquals(MatchFixture.MATCH_PAGE, result);
        verify(matchRepository).findByIdContains(anyString(), any(Pageable.class));
    }

    @Test
    @DisplayName("매치 삭제 시 Match 캐시, Participant 캐시, DB 데이터 모두 삭제한다.")
    void deleteById() {
        given(matchCache.get(MATCH_ID, Match.class))
                .willReturn(null);
        given(matchRepository.findByIdFetchParticipant(MATCH_ID))
                .willReturn(Optional.of(MATCH));

        matchDAO.deleteById(MATCH_ID);

        verify(matchCache).get(anyString(), ArgumentMatchers.<Class<Match>>any());
        verify(matchRepository).findByIdFetchParticipant(anyString());
        verify(participantCache).evict(anyString());
        verify(matchCache).evict(anyString());
        verify(matchRepository).deleteById(anyString());
    }

    @Test
    @DisplayName("존재하지 않는 매치 삭제 시 MatchNotFoundException 발생")
    void deleteById_throw_matchNotFoundException() {
        given(matchCache.get(MATCH_ID, Match.class))
                .willReturn(null);
        given(matchRepository.findByIdFetchParticipant(MATCH_ID))
                .willReturn(Optional.empty());

        assertThrows(MatchNotFoundException.class, () -> matchDAO.deleteById(MATCH_ID));

        verify(matchCache).get(anyString(), ArgumentMatchers.<Class<Match>>any());
        verify(matchRepository).findByIdFetchParticipant(anyString());
        verify(participantCache, never()).evict(anyString());
        verify(matchCache, never()).evict(anyString());
        verify(matchRepository, never()).deleteById(anyString());
    }
}