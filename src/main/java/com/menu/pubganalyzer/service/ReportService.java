package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.Analyzer;
import com.menu.pubganalyzer.domain.Telemetry;
import com.menu.pubganalyzer.domain.dao.MatchDAO;
import com.menu.pubganalyzer.domain.dao.ParticipantDAO;
import com.menu.pubganalyzer.domain.dao.TelemetryDAO;
import com.menu.pubganalyzer.domain.dto.AnalyzerRes;
import com.menu.pubganalyzer.domain.dto.MatchInfoRes;
import com.menu.pubganalyzer.domain.dto.MatchResultRes;
import com.menu.pubganalyzer.domain.dto.ReportRes;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Participant;
import com.menu.pubganalyzer.domain.model.Roster;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {
    private final MatchDAO matchDAO;
    private final ParticipantDAO participantDAO;
    private final TelemetryDAO telemetryDAO;

    @Transactional
    public ReportRes report(String matchId, String nickname) {
        Match match = matchDAO.findById(matchId);
        Participant participant = participantDAO.findByMatchIdAndPlayerName(match.getId(), nickname);

        Roster roster = participant.getRoster();

        Telemetry rosterTelemetry = telemetryDAO.findByMatchAndRoster(match, roster);
        Analyzer analyzer = Analyzer.of(rosterTelemetry, nickname);

        return ReportRes.of(
                MatchInfoRes.of(match),
                MatchResultRes.of(match, participant),
                AnalyzerRes.of(analyzer)
        );
    }
}
