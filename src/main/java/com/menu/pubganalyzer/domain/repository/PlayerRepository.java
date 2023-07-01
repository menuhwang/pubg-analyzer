package com.menu.pubganalyzer.domain.repository;

import com.menu.pubganalyzer.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, String> {
    @Query("SELECT p FROM player p WHERE p.name = :nickname")
    Optional<Player> findByName(String nickname);
}