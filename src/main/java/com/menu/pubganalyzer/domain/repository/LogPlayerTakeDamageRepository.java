package com.menu.pubganalyzer.domain.repository;

import com.menu.pubganalyzer.domain.model.LogPlayerTakeDamage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface LogPlayerTakeDamageRepository extends JpaRepository<LogPlayerTakeDamage, Integer> {
    List<LogPlayerTakeDamage> findByAttackerNameInAndVictimNameInAndMatchId(Collection<String> nicknames, Collection<String> victims, String matchId);
    boolean existsByMatchId(String matchId);
}