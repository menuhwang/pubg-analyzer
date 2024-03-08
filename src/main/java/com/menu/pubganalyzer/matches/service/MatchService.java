package com.menu.pubganalyzer.matches.service;

import com.menu.pubganalyzer.matches.dto.request.MatchFindCondition;
import com.menu.pubganalyzer.matches.model.Match;
import com.menu.pubganalyzer.matches.model.Participant;
import com.menu.pubganalyzer.matches.model.Roster;
import com.menu.pubganalyzer.common.exception.MatchNotFoundException;
import com.menu.pubganalyzer.matches.dto.response.MatchInfoResponse;
import com.menu.pubganalyzer.matches.dto.response.MatchResultResponse;
import com.menu.pubganalyzer.matches.dto.response.RosterResponse;
import com.menu.pubganalyzer.matches.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;

    public Page<Match> findAll(Pageable pageable) {
        return matchRepository.findAll(pageable);
    }

    public Match findById(String id) {
        return matchRepository.findById(id)
                .orElseThrow(MatchNotFoundException::new);
    }
    public Page<Match> findByPlayerName(
            final String playerName,
            final Pageable pageable,
            final MatchFindCondition matchFindCondition) {
        return matchRepository.findByPlayerName(playerName, pageable, matchFindCondition);
    }

    public MatchInfoResponse findMatchInfo(final String id) {
        return matchRepository.findById(id)
                .map(MatchInfoResponse::from)
                .orElseThrow(MatchNotFoundException::new);
    }

    public MatchResultResponse findMatchResultByPlayer(
            final String id,
            final String playerName) {
        Match match = matchRepository.findById(id)
                .orElseThrow(MatchNotFoundException::new);
        Roster roster = match.getRosterByName(playerName);
        Participant participant = roster.getParticipantByName(playerName);

        return MatchResultResponse.of(match, roster, participant);
    }

    public RosterResponse findRoster(
            final String id,
            final String playerName) {
        Match match = matchRepository.findById(id)
                .orElseThrow(MatchNotFoundException::new);
        Roster roster = match.getRosterByName(playerName);

        return RosterResponse.from(roster);
    }

    @Transactional
    public void deleteById(Collection<String> ids) {
        for (String id : ids) {
            matchRepository.deleteById(id);
        }
    }
}
