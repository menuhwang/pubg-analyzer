package com.menu.pubganalyzer.domain.dao.impl;

import com.menu.pubganalyzer.domain.dao.ParticipantDAO;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Participant;
import com.menu.pubganalyzer.domain.repository.ParticipantRepository;
import com.menu.pubganalyzer.exception.ParticipantNotFoundException;
import com.menu.pubganalyzer.support.fixture.MatchFixture;
import com.menu.pubganalyzer.support.fixture.PlayerFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.cache.Cache;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class ParticipantDAOTest {
    private final ParticipantRepository participantRepository = Mockito.mock(ParticipantRepository.class);
    private final Cache matchCache = Mockito.mock(Cache.class);
    private final Cache participantCache = Mockito.mock(Cache.class);

    private final ParticipantDAO participantDAO = new ParticipantDAOImpl(participantRepository, matchCache, participantCache);

    private static final String PLAYER_NAME = PlayerFixture.PLAYER_NAME;
    private static final String MATCH_ID = MatchFixture.MATCH_ID;
    private static final Participant PARTICIPANT = MatchFixture.PARTICIPANT;
    private static final Match MATCH = MatchFixture.MATCH;

    private static final String KEY = MATCH_ID + "_" + PLAYER_NAME;

    @Test
    @DisplayName("Participant 캐시 히트 : Participant 캐시에서 반환한다.")
    void findByMatchIdAndPlayerName_fetch_participant_cache() {
        given(participantCache.get(KEY, Participant.class))
                .willReturn(PARTICIPANT);

        Participant result = participantDAO.findByMatchIdAndPlayerName(MATCH_ID, PLAYER_NAME);

        assertEquals(PARTICIPANT, result);
        verify(participantCache).get(anyString(), ArgumentMatchers.<Class<Participant>>any());
        verify(matchCache, never()).get(anyString(), ArgumentMatchers.<Class<Match>>any());
        verify(participantRepository, never()).findByMatchIdAndPlayerName(anyString(), anyString());
    }

    @Test
    @DisplayName("Participant 캐시 미스, Match 캐시 히트 : Match 캐시의 match에서 Participant를 찾아 캐시에 저장 후 반환한다.")
    void findByMatchIdAndPlayerName_fetch_match_cache() {
        given(participantCache.get(KEY, Participant.class))
                .willReturn(null);
        given(matchCache.get(MATCH_ID, Match.class))
                .willReturn(MATCH);

        Participant result = participantDAO.findByMatchIdAndPlayerName(MATCH_ID, PLAYER_NAME);

        assertEquals(PARTICIPANT, result);
        verify(participantCache).get(anyString(), ArgumentMatchers.<Class<Participant>>any());
        verify(matchCache).get(anyString(), ArgumentMatchers.<Class<Match>>any());
        verify(participantRepository, never()).findByMatchIdAndPlayerName(anyString(), anyString());
        verify(participantCache).put(KEY, PARTICIPANT);
    }

    @Test
    @DisplayName("Participant 캐시 미스, Match 캐시 미스 : DB에서 검색하여 캐시에 저장 후 반환한다.")
    void findByMatchIdAndPlayerName_fetch_participant_repository() {
        given(participantCache.get(KEY, Participant.class))
                .willReturn(null);
        given(matchCache.get(MATCH_ID, Match.class))
                .willReturn(null);
        given(participantRepository.findByMatchIdAndPlayerName(MATCH_ID, PLAYER_NAME))
                .willReturn(Optional.of(PARTICIPANT));

        Participant result = participantDAO.findByMatchIdAndPlayerName(MATCH_ID, PLAYER_NAME);

        assertEquals(PARTICIPANT, result);
        verify(participantCache).get(anyString(), ArgumentMatchers.<Class<Participant>>any());
        verify(matchCache).get(anyString(), ArgumentMatchers.<Class<Match>>any());
        verify(participantRepository).findByMatchIdAndPlayerName(anyString(), anyString());
        verify(participantCache).put(KEY, PARTICIPANT);
    }

    @Test
    @DisplayName("Participant 캐시 미스, Match 캐시 미스, DB not found : ParticipantNotFoundException 발생")
    void findByMatchIdAndPlayerName_throw_participantNotFoundException() {
        given(participantCache.get(KEY, Participant.class))
                .willReturn(null);
        given(matchCache.get(MATCH_ID, Match.class))
                .willReturn(null);
        given(participantRepository.findByMatchIdAndPlayerName(MATCH_ID, PLAYER_NAME))
                .willReturn(Optional.empty());

        assertThrows(ParticipantNotFoundException.class, () -> participantDAO.findByMatchIdAndPlayerName(MATCH_ID, PLAYER_NAME));

        verify(participantCache).get(anyString(), ArgumentMatchers.<Class<Participant>>any());
        verify(matchCache).get(anyString(), ArgumentMatchers.<Class<Match>>any());
        verify(participantRepository).findByMatchIdAndPlayerName(anyString(), anyString());
        verify(participantCache, never()).put(KEY, PARTICIPANT);
    }
}