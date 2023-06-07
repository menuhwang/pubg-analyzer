package com.menu.pubganalyzer.domain.dao.impl;

import com.menu.pubganalyzer.domain.dao.ParticipantDAO;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Participant;
import com.menu.pubganalyzer.domain.repository.ParticipantRepository;
import com.menu.pubganalyzer.exception.ParticipantNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ParticipantDAOImpl implements ParticipantDAO {
    private final ParticipantRepository participantRepository;

    private final Cache matchCache;
    private final Cache participantCache;

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
