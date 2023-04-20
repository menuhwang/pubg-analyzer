package com.menu.pubganalyzer.domain.repository;

import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface MatchRepository extends JpaRepository<Match, String> {
    @Query("SELECT m FROM Match m join fetch m.rosters join fetch m.asset WHERE m.shardId = :shard AND m.id IN :ids")
    Set<Match> findByShardIdAndIdIn(Shard shard, Collection<String> ids);

    @Query("SELECT m FROM Match m join fetch m.rosters r join fetch r.participants WHERE m.id = :id ORDER BY m.createdAt DESC")
    Optional<Match> findByIdFetchParticipant(String id);
}