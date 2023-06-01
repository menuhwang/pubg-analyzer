package com.menu.pubganalyzer.domain.dao.impl;

import com.menu.pubganalyzer.config.CacheType;
import com.menu.pubganalyzer.domain.dao.PlayerDAO;
import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.domain.repository.PlayerRepository;
import com.menu.pubganalyzer.exception.PlayerNotFoundException;
import com.menu.pubganalyzer.util.pubgAPI.PubgAPI;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayerDAOImpl implements PlayerDAO {
    private final Cache cache;
    private final PlayerRepository playerRepository;
    private final PubgAPI pubgAPI;

    public PlayerDAOImpl(PlayerRepository playerRepository, PubgAPI pubgAPI, CacheManager cacheManager) {
        this.playerRepository = playerRepository;
        this.pubgAPI = pubgAPI;
        this.cache = cacheManager.getCache(CacheType.PLAYER.getCacheName());
    }

    @Override
    public Player findByNickname(String nickname) {
        Player player = cache.get(nickname, Player.class);
        if (player != null) return player;
        return playerRepository.findByName(nickname)
                .orElseThrow(PlayerNotFoundException::new);
    }

    @Override
    public Player fetch(String nickname) {
        Player player = cache.get(nickname, Player.class);
        if (player != null) return player;
        player = Player.of(pubgAPI.player(Shard.STEAM.name(), List.of(nickname))).get(0);
        cache.put(nickname, player);
        if (!playerRepository.existsById(player.getId())) save(player);
        return player;
    }

    @Override
    public void save(Player player) {
        playerRepository.save(player);
    }
}
