package com.menu.pubganalyzer.domain.repository;

import com.menu.pubganalyzer.domain.model.LogPlayerKillV2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface LogPlayerKillV2Repository extends JpaRepository<LogPlayerKillV2, Integer> {
    List<LogPlayerKillV2> findByKillerNameInAndMatchId(Collection<String> nicknames, String matchId);
    boolean existsByMatchId(String matchId);
}