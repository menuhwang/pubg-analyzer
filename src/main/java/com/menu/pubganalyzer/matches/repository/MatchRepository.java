package com.menu.pubganalyzer.matches.repository;

import com.menu.pubganalyzer.matches.model.Match;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MatchRepository extends MongoRepository<Match, String>, MatchCustomRepository {
    Optional<Match> findById(String id);
}