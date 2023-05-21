package com.menu.pubganalyzer.domain.repository;

import com.menu.pubganalyzer.domain.model.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, String> {
    void deleteByIdIn(Collection<String> ids);
    Page<Match> findByIdContains(String id, Pageable pageable);

    @Query("SELECT m FROM Match m join fetch m.rosters r join fetch r.participants WHERE m.id = :id ORDER BY m.createdAt DESC")
    Optional<Match> findByIdFetchParticipant(String id);
}