package com.menu.pubganalyzer.domain.dao.impl;

import com.menu.pubganalyzer.config.CacheType;
import com.menu.pubganalyzer.domain.dao.ParticipantDAO;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Participant;
import com.menu.pubganalyzer.domain.repository.ParticipantRepository;
import com.menu.pubganalyzer.exception.ParticipantNotFoundException;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

@Repository
public class ParticipantDAOImpl implements ParticipantDAO {
    private final ParticipantRepository participantRepository;

    private final Cache matchCache;
    private final Cache participantCache;

    public ParticipantDAOImpl(ParticipantRepository participantRepository, CacheManager cacheManager) {
        this.participantRepository = participantRepository;
        this.matchCache = cacheManager.getCache(CacheType.MATCH.getCacheName());
        this.participantCache = cacheManager.getCache(CacheType.PARTICIPANT.getCacheName());
    }

    @Override
    public Participant findByMatchIdAndPlayerName(String matchId, String playerName) {
        String participantKey = matchId + "_" + playerName;
        Participant participant = participantCache.get(participantKey, Participant.class);
        if (participant != null) return participant;
        Match match = matchCache.get(matchId, Match.class);
        if (match != null) {
            participant = match.getParticipant(playerName);
        } else {
            participant = participantRepository.findByMatchIdAndPlayerName(matchId, playerName)
                    .orElseThrow(ParticipantNotFoundException::new);
        }
        participantCache.put(participantKey, participant);
        return participant;
    }
}
