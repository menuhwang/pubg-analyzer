package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.Analyzer;
import com.menu.pubganalyzer.domain.dto.AnalyzerRes;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Participant;
import com.menu.pubganalyzer.domain.model.Roster;
import com.menu.pubganalyzer.domain.repository.MatchRepository;
import com.menu.pubganalyzer.domain.repository.ParticipantRepository;
import com.menu.pubganalyzer.exception.MatchNotFoundException;
import com.menu.pubganalyzer.exception.PlayerNotFoundException;
import com.menu.pubganalyzer.facade.TelemetryFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalyzerService {
    private final ParticipantRepository participantRepository;
    private final MatchRepository matchRepository;
    private final TelemetryFacade telemetryFacade;

    @Transactional
    public AnalyzerRes analyze(String matchId, String nickname) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(MatchNotFoundException::new);
        Participant participant = participantRepository.findByNameAndMatch(nickname, match)
                .orElseThrow(PlayerNotFoundException::new);

        Roster team = participant.getRoster();

        Analyzer teamLog = telemetryFacade.findLogs(match, team);
        Analyzer analyzer = teamLog.filterOfKiller(nickname);
        return AnalyzerRes.of(analyzer, match.getCreatedAt());
    }
}
