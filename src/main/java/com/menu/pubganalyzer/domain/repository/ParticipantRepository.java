package com.menu.pubganalyzer.domain.repository;

import com.menu.pubganalyzer.domain.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ParticipantRepository extends JpaRepository<Participant, String> {
    @Query("select p from participant p join fetch p.roster r join fetch r.participants join fetch r.match m join fetch m.rosters where p.match.id = :matchId and p.name = :playerName")
    Optional<Participant> findByMatchIdAndPlayerName(String matchId, String playerName);
}