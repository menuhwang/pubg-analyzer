package com.menu.pubganalyzer.domain.repository;

import com.menu.pubganalyzer.domain.model.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, String> {
    void deleteByIdIn(Collection<String> ids);

    void deleteByCreatedAtGreaterThanEqualAndCreatedAtLessThan(LocalDateTime date1, LocalDateTime date2);

    void deleteByCreatedAtGreaterThanEqual(LocalDateTime date1);

    void deleteByCreatedAtLessThan(LocalDateTime date1);

    Page<Match> findByIdContains(String id, Pageable pageable);

    @Query("SELECT m FROM matches m join fetch m.rosters r join fetch r.participants join fetch m.asset WHERE m.id = :id ORDER BY m.createdAt DESC")
    Optional<Match> findByIdFetchParticipant(String id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("SELECT m FROM matches m WHERE m.id = :id")
    Optional<Match> findByIdShardLock(String id);
}