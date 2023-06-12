package com.menu.pubganalyzer.domain.dao.impl;

import com.menu.pubganalyzer.domain.dao.PlayerDAO;
import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.domain.repository.PlayerRepository;
import com.menu.pubganalyzer.exception.PlayerNotFoundException;
import com.menu.pubganalyzer.support.fixture.PlayerFixture;
import com.menu.pubganalyzer.support.fixture.pubgapi.PlayerResponseFixture;
import com.menu.pubganalyzer.util.pubgAPI.PubgAPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.cache.Cache;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class PlayerDAOTest {
    private final Cache playerCache = Mockito.mock(Cache.class);
    private final PlayerRepository playerRepository = Mockito.mock(PlayerRepository.class);
    private final PubgAPI pubgAPI = Mockito.mock(PubgAPI.class);

    private final PlayerDAO playerDAO = new PlayerDAOImpl(playerCache, playerRepository, pubgAPI);

    private final String PLAYER_NAME = PlayerFixture.PLAYER_NAME;
    private final Player PLAYER = PlayerFixture.PLAYER;

    @Test
    @DisplayName("닉네임으로 검색 Player 캐시 히트 : Player 캐시에서 반환한다.")
    void findByNickname_fetch_player_cache() {
        given(playerCache.get(PLAYER_NAME, Player.class))
                .willReturn(PLAYER);

        Player result = playerDAO.findByNickname(PLAYER_NAME);

        assertEquals(PLAYER, result);
        verify(playerCache).get(anyString(), ArgumentMatchers.<Class<Player>>any());
        verify(playerRepository, never()).findByName(anyString());
    }

    @Test
    @DisplayName("닉네임으로 검색 Player 캐시 미스 : DB에서 반환한다.")
    void findByNickname_fetch_player_repository() {
        given(playerCache.get(PLAYER_NAME, Player.class))
                .willReturn(null);
        given(playerRepository.findByName(PLAYER_NAME))
                .willReturn(Optional.of(PLAYER));

        Player result = playerDAO.findByNickname(PLAYER_NAME);

        assertEquals(PLAYER, result);
        verify(playerCache).get(anyString(), ArgumentMatchers.<Class<Player>>any());
        verify(playerRepository).findByName(anyString());
    }

    @Test
    @DisplayName("닉네임으로 검색 Player 캐시 미스, DB not found : PlayerNotFoundException 발생")
    void findByNickname_throw_playerNotFoundException() {
        given(playerCache.get(PLAYER_NAME, Player.class))
                .willReturn(null);
        given(playerRepository.findByName(PLAYER_NAME))
                .willReturn(Optional.empty());

        assertThrows(PlayerNotFoundException.class, () -> playerDAO.findByNickname(PLAYER_NAME));

        verify(playerCache).get(anyString(), ArgumentMatchers.<Class<Player>>any());
        verify(playerRepository).findByName(anyString());
    }

    @Test
    @DisplayName("Player api 요청 Player 캐시 히트 : Player 캐시에서 반환한다.")
    void fetch_fetch_player_cache() {
        given(playerCache.get(PLAYER_NAME, Player.class))
                .willReturn(PLAYER);

        Player result = playerDAO.fetch(PLAYER_NAME);

        assertEquals(PLAYER, result);
        verify(playerCache).get(anyString(), ArgumentMatchers.<Class<Player>>any());
        verify(pubgAPI, never()).player(anyString(), anyCollection());
        verify(playerCache, never()).put(anyString(), any(Player.class));
        verify(playerRepository, never()).existsById(anyString());
    }

    @Test
    @DisplayName("Player api 요청 Player 캐시 미스 : api 요청 결과를 캐시에 저장 후 반환한다.")
    void fetch_fetch_player_api() {
        given(playerCache.get(PLAYER_NAME, Player.class))
                .willReturn(null);
        given(pubgAPI.player(eq(PlayerFixture.PLAYER_SHARD), eq(List.of(PLAYER_NAME))))
                .willReturn(PlayerResponseFixture.PLAYERS_RESPONSE);
        given(playerRepository.existsById(anyString())).willReturn(true);

        Player result = playerDAO.fetch(PLAYER_NAME);

        assertEquals(PLAYER.getName(), result.getName());
        verify(playerCache).get(anyString(), ArgumentMatchers.<Class<Player>>any());
        verify(pubgAPI).player(anyString(), anyCollection());
        verify(playerCache).put(anyString(), any(Player.class));
        verify(playerRepository).existsById(anyString());
    }

    @Test
    @DisplayName("Player 저장")
    void save() {
        playerDAO.save(PLAYER);
        verify(playerRepository).save(any(Player.class));
    }
}