package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.dao.MatchDAO;
import com.menu.pubganalyzer.domain.dao.ParticipantDAO;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Participant;
import com.menu.pubganalyzer.domain.model.Roster;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindService {
    private final MatchDAO matchDAO;
    private final ParticipantDAO participantDAO;

    public Roster findRosterByMatchIdAndPlayer(String matchId, String nickname) {
        Match match = matchDAO.findById(matchId);

        Participant participant = participantDAO.findByMatchIdAndPlayerName(match.getId(), nickname);

        return participant.getRoster();
    }
}
