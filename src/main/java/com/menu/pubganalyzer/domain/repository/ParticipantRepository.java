package com.menu.pubganalyzer.domain.repository;

import com.menu.pubganalyzer.domain.model.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParticipantRepository extends JpaRepository<Participant, String> {
    Page<Participant> findByPlayerIdOrderByMatch_CreatedAtDesc(String playerId, Pageable pageable);
}