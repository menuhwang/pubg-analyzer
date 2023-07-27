package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.repository.MatchRepository;
import com.menu.pubganalyzer.exception.MatchNotFoundException;
import com.menu.pubganalyzer.support.fixture.MatchFixture;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.menu.pubganalyzer.support.fixture.MatchFixture.MATCH_ID;
import static com.menu.pubganalyzer.support.fixture.MatchFixture.PAGEABLE;
import static com.menu.pubganalyzer.support.fixture.PlayerFixture.PLAYER_NAME;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class MatchServiceTest {
    private final MatchRepository matchRepository = Mockito.mock(MatchRepository.class);
    private final MatchService matchService = new MatchService(matchRepository);

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
}