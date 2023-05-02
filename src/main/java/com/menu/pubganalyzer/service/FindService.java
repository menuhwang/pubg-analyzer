package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Participant;
import com.menu.pubganalyzer.domain.model.Roster;
import com.menu.pubganalyzer.domain.repository.MatchRepository;
import com.menu.pubganalyzer.domain.repository.ParticipantRepository;
import com.menu.pubganalyzer.exception.MatchNotFoundException;
import com.menu.pubganalyzer.exception.PlayerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindService {
    private final MatchRepository matchRepository;
    private final ParticipantRepository participantRepository;

    public Roster findRosterByMatchIdAndPlayer(String matchId, String nickname) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(MatchNotFoundException::new);

        Participant participant = participantRepository.findByNameAndMatch(nickname, match)
                .orElseThrow(PlayerNotFoundException::new);

        return participant.getRoster();
    }
}
