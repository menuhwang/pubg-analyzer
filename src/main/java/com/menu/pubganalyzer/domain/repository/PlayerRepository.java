package com.menu.pubganalyzer.domain.repository;

import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, String> {
    Optional<Player> findByShardIdAndName(Shard shard, String nickname);
}