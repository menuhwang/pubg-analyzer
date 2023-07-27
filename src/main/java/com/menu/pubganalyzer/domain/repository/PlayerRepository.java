package com.menu.pubganalyzer.domain.repository;

import com.menu.pubganalyzer.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
}