package com.menu.pubganalyzer.players.repository;

import com.menu.pubganalyzer.players.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, String> {
    Optional<Player> findByName(String nickname);
}