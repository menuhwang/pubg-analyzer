package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.repository.PlayerRepository;
import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.util.pubgAPI.PubgAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PubgAPI pubgAPI;

    public Player getPlayerByNickname(Shard shard, String nickname) {
        pubgAPI.setShard(shard);

        return playerRepository.findByShardIdAndName(shard, nickname)
                                .orElseGet(() -> {
                                    Player player = pubgAPI.player(nickname);
                                    playerRepository.save(player);
                                    return player;
                                });
    }
}
