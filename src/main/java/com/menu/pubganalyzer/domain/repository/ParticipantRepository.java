package com.menu.pubganalyzer.domain.repository;

import com.menu.pubganalyzer.domain.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ParticipantRepository extends JpaRepository<Participant, String> {
    @Query("select p from Participant p join fetch p.roster r join fetch r.participants where p.match.id = :matchId and p.playerId = :playerId")
    Optional<Participant> findByMatchIdAndPlayerId(String matchId, String playerId);
}