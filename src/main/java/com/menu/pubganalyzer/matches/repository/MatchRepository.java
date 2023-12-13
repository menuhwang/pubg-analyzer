package com.menu.pubganalyzer.matches.repository;

import com.menu.pubganalyzer.matches.model.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MatchRepository extends MongoRepository<Match, String> {
    Optional<Match> findById(String id);

    Page<Match> findByRosters_Participants_Name(String playerName, Pageable pageable);
}