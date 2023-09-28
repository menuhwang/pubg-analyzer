package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.matches.dto.response.MatchInfoResponse;
import com.menu.pubganalyzer.matches.dto.response.MatchResultResponse;
import com.menu.pubganalyzer.matches.dto.response.RosterResponse;
import com.menu.pubganalyzer.matches.repository.MatchRepository;
import com.menu.pubganalyzer.common.exception.MatchNotFoundException;
import com.menu.pubganalyzer.matches.service.MatchService;
import com.menu.pubganalyzer.support.fixture.MatchFixture;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.menu.pubganalyzer.support.fixture.MatchFixture.MATCH_ID;
import static com.menu.pubganalyzer.support.fixture.MatchFixture.PAGEABLE;
import static com.menu.pubganalyzer.support.fixture.PlayerFixture.PLAYER_NAME;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class MatchServiceTest {
    private final MatchRepository matchRepository = Mockito.mock(MatchRepository.class);
    private final MatchService matchService = new MatchService(matchRepository);
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void findAll() {
        given(matchRepository.findAll(PAGEABLE))
                .willReturn(MatchFixture.MATCH_PAGE);

        assertDoesNotThrow(() -> matchService.findAll(PAGEABLE));

        verify(matchRepository).findAll(PAGEABLE);
    }

    @Test
    void findById() {
        given(matchRepository.findById(MATCH_ID))
                .willReturn(Optional.of(MatchFixture.MATCH));

        assertDoesNotThrow(() -> matchService.findById(MATCH_ID));

        verify(matchRepository).findById(MATCH_ID);
    }

    @Test
    void findByIdWhenNotFoundMatch() {
        given(matchRepository.findById(MATCH_ID))
                .willReturn(Optional.empty());

        assertThrows(MatchNotFoundException.class, () -> matchService.findById(MATCH_ID));

        verify(matchRepository).findById(MATCH_ID);
    }

    @Test
    void findByPlayerName() {
        given(matchRepository.findByRosters_Participants_Name(PLAYER_NAME, PAGEABLE))
                .willReturn(MatchFixture.MATCH_PAGE);

        assertDoesNotThrow(() -> matchService.findByPlayerName(PLAYER_NAME, PAGEABLE));

        verify(matchRepository).findByRosters_Participants_Name(PLAYER_NAME, PAGEABLE);
    }

    @Test
    void deleteById() {
        int cases = 5;
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < cases; i++) {
            ids.add(UUID.randomUUID().toString());
        }

        assertDoesNotThrow(() -> matchService.deleteById(ids));

        ids.forEach(id -> verify(matchRepository).deleteById(id));
    }

    @Test
    void findMatchInfo() {
        given(matchRepository.findById(MATCH_ID))
                .willReturn(Optional.of(MatchFixture.MATCH));

        MatchInfoResponse result = assertDoesNotThrow(() -> matchService.findMatchInfo(MATCH_ID));

        logger.info("match info: {}", result);
    }

    @Test
    void findMatchResultByPlayer() {
        given(matchRepository.findById(MATCH_ID))
                .willReturn(Optional.of(MatchFixture.MATCH));

        MatchResultResponse result = assertDoesNotThrow(() -> matchService.findMatchResultByPlayer(MATCH_ID, PLAYER_NAME));

        logger.info("match result: {}", result);
    }

    @Test
    void findRosterWhenJustSelf() {
        given(matchRepository.findById(MATCH_ID))
                .willReturn(Optional.of(MatchFixture.MATCH));

        RosterResponse result = assertDoesNotThrow(() -> matchService.findRoster(MATCH_ID, PLAYER_NAME));

        logger.info("roster result: {}", result);
    }
}