package com.menu.pubganalyzer.domain.dao.impl;

import com.menu.pubganalyzer.domain.dao.MatchDAO;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.domain.repository.MatchRepository;
import com.menu.pubganalyzer.exception.MatchNotFoundException;
import com.menu.pubganalyzer.util.pubgAPI.PubgAPI;
import com.menu.pubganalyzer.util.pubgAPI.exception.PubgAPIMatchNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class MatchDAOImpl implements MatchDAO {
    private final MatchRepository matchRepository;
    private final PubgAPI pubgAPI;
    private final Cache matchCache;
    private final Cache participantCache;

    @Override
    public Match findById(String id) {
        Match match  = matchCache.get(id, Match.class);
        if (match != null) return match;

        Optional<Match> optionalMatch = matchRepository.findByIdFetchParticipant(id);
        if (optionalMatch.isPresent()) {
            match = optionalMatch.get();
        } else {
            try {
                match = Match.of(pubgAPI.match(Shard.STEAM.name(), id));
            } catch (PubgAPIMatchNotFoundException e) {
                throw new MatchNotFoundException(id);
            }
        }
        matchCache.put(id, match);
        return match;
    }

    @Override
    public Page<Match> findById(String id, Pageable pageable) {
        return matchRepository.findByIdContains(id, pageable);
    }

    @Override
    public void deleteById(String id) {
        Match match = matchCache.get(id, Match.class);
        if (match == null) {
            match = matchRepository.findByIdFetchParticipant(id)
                    .orElseThrow(MatchNotFoundException::new);
        }
        Set<String> playerNames = match.extractPlayerNames();
        for (String name : playerNames) {
            participantCache.evict(id + "_" + name);
        }
        matchCache.evict(id);
        matchRepository.deleteById(id);
    }
}
