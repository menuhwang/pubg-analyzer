package com.menu.pubganalyzer.domain.repository;

import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ParticipantRepository extends JpaRepository<Participant, String> {
    Page<Participant> findByPlayerIdOrderByRoster_Match_CreatedAtDesc(String playerId, Pageable pageable);

    @Query("select p from Participant p join fetch p.roster r join fetch r.participants where p.name = :nickname and p.match = :match")
    Optional<Participant> findByNameAndMatch(String nickname, Match match);
}