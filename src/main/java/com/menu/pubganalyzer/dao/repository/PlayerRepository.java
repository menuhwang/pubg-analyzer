package com.menu.pubganalyzer.dao.repository;

import com.menu.pubganalyzer.model.Player;
import com.menu.pubganalyzer.model.enums.Shard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, String> {
    Optional<Player> findByShardIdAndName(Shard shard, String nickname);
}