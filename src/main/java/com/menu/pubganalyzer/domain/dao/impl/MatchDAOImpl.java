package com.menu.pubganalyzer.domain.dao.impl;

import com.menu.pubganalyzer.domain.dao.MatchDAO;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.domain.repository.MatchRepository;
import com.menu.pubganalyzer.exception.MatchNotFoundException;
import com.menu.pubganalyzer.util.pubgAPI.PubgAPI;
import com.menu.pubganalyzer.util.pubgAPI.exception.PubgAPIMatchNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MatchDAOImpl implements MatchDAO {
    private final MatchRepository matchRepository;
    private final PubgAPI pubgAPI;

    @Override
    @Cacheable(cacheNames = "match", key = "#id")
    public Match findById(String id) {
        Optional<Match> optionalMatch = matchRepository.findByIdFetchParticipant(id);
        if (optionalMatch.isPresent()) return optionalMatch.get();

        try {
            return Match.of(pubgAPI.match(Shard.STEAM.name(), id));
        } catch (PubgAPIMatchNotFoundException e) {
            throw new MatchNotFoundException(id);
        }
    }
}
