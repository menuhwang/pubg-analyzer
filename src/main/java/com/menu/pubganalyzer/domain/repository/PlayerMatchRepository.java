package com.menu.pubganalyzer.domain.repository;

import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.domain.model.PlayerMatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PlayerMatchRepository extends JpaRepository<PlayerMatch, Long> {
    Page<PlayerMatch> findByPlayer(Player player, Pageable pageable);
    Set<PlayerMatch> findByPlayer(Player player);
    void deleteByMatchId(String matchId);
}