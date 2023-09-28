package com.menu.pubganalyzer.players.repository;

import com.menu.pubganalyzer.players.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
}