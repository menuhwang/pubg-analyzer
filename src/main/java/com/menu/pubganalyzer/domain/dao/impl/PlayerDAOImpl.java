package com.menu.pubganalyzer.domain.dao.impl;

import com.menu.pubganalyzer.domain.dao.PlayerDAO;
import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.domain.repository.PlayerRepository;
import com.menu.pubganalyzer.exception.PlayerNotFoundException;
import com.menu.pubganalyzer.util.pubgAPI.PubgAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlayerDAOImpl implements PlayerDAO {
    private final Cache playerCache;
    private final PlayerRepository playerRepository;
    private final PubgAPI pubgAPI;

    @Override
    public Player findByNickname(String nickname) {
        Player player = playerCache.get(nickname, Player.class);
        if (player != null) return player;
        return playerRepository.findByName(nickname)
                .orElseThrow(PlayerNotFoundException::new);
    }

    @Override
    public Player fetch(String nickname) {
        Player player = playerCache.get(nickname, Player.class);
        if (player != null) return player;
        player = Player.of(pubgAPI.player(Shard.STEAM.name(), List.of(nickname))).get(0);
        playerCache.put(nickname, player);
        if (!playerRepository.existsById(player.getId())) save(player);
        return player;
    }

    @Override
    public void save(Player player) {
        playerRepository.save(player);
    }
}
