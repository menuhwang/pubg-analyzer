package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.dto.MatchInfoRes;
import com.menu.pubganalyzer.domain.dto.MatchResultRes;
import com.menu.pubganalyzer.domain.model.matches.Match;
import com.menu.pubganalyzer.domain.model.matches.Participant;
import com.menu.pubganalyzer.domain.model.matches.Roster;
import com.menu.pubganalyzer.domain.repository.MatchRepository;
import com.menu.pubganalyzer.exception.MatchNotFoundException;
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
            final Pageable pageable) {
        return matchRepository.findByRosters_Participants_Name(playerName, pageable);
    }

    public MatchInfoRes findMatchInfo(final String id) {
        return matchRepository.findById(id)
                .map(MatchInfoRes::from)
                .orElseThrow(MatchNotFoundException::new);
    }

    public MatchResultRes findMatchResultByPlayer(
            final String id,
            final String playerName) {
        Match match = matchRepository.findById(id)
                .orElseThrow(MatchNotFoundException::new);
        Roster roster = match.getRosterByName(playerName);
        Participant participant = roster.getParticipantByName(playerName);

        return MatchResultRes.of(match, roster, participant);
    }

    @Transactional
    public void deleteById(Collection<String> ids) {
        for (String id : ids) {
            matchRepository.deleteById(id);
        }
    }
}
