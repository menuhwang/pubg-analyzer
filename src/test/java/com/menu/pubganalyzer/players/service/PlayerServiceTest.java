package com.menu.pubganalyzer.players.service;

import com.menu.pubganalyzer.fetch.service.PubgService;
import com.menu.pubganalyzer.matches.repository.MatchRepository;
import com.menu.pubganalyzer.players.repository.PlayerRepository;
import com.menu.pubganalyzer.players.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.menu.pubganalyzer.support.fixture.MatchFixture.MATCH;
import static com.menu.pubganalyzer.support.fixture.PlayerFixture.PLAYER;
import static com.menu.pubganalyzer.support.fixture.PlayerFixture.PLAYER_NAME;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PlayerServiceTest {
    private final PubgService pubgService = Mockito.mock(PubgService.class);
    private final PlayerRepository playerRepository = Mockito.mock(PlayerRepository.class);
    private final MatchRepository matchRepository = Mockito.mock(MatchRepository.class);

    private final PlayerService playerService = new PlayerService(
            pubgService,
            playerRepository,
            matchRepository
    );

    @Test
    void updateMatchHistory() {
        given(pubgService.fetchPlayer(PLAYER_NAME))
                .willReturn(PLAYER);

        when(matchRepository.existsById(anyString()))
                .thenReturn(false);

        when(pubgService.fetchMatches(anyCollection()))
                .thenReturn(List.of(MATCH));

        assertDoesNotThrow(() -> playerService.updateMatchHistory(PLAYER_NAME));

        verify(pubgService).fetchPlayer(PLAYER_NAME);
        verify(matchRepository).existsById(anyString());
        verify(pubgService).fetchMatches(anyCollection());

        verify(playerRepository).save(any());
        verify(matchRepository).saveAll(anyCollection());
    }
}