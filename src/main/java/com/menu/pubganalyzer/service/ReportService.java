package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.dto.AnalyzerRes;
import com.menu.pubganalyzer.domain.dto.MatchInfoRes;
import com.menu.pubganalyzer.domain.dto.MatchResultRes;
import com.menu.pubganalyzer.domain.dto.ReportRes;
import com.menu.pubganalyzer.domain.model.matches.Match;
import com.menu.pubganalyzer.domain.model.matches.Participant;
import com.menu.pubganalyzer.domain.model.matches.Roster;
import com.menu.pubganalyzer.domain.model.telemetries.LogPlayerKillV2;
import com.menu.pubganalyzer.domain.model.telemetries.LogPlayerTakeDamage;
import com.menu.pubganalyzer.domain.Analyzer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {
    private final TelemetryService telemetryService;

    @Transactional
    public ReportRes getMatchReport(Match match, String nickname) {
        Roster roster = match.getRosterByName(nickname);
        Participant participant = roster.getParticipantByName(nickname);

        if (!telemetryService.existsTelemetry(match)) {
            telemetryService.fetchTelemetry(match);
        }

        Analyzer analyzer = Analyzer.init();

        List<LogPlayerKillV2> logPlayerKillV2s = telemetryService.findKillLogs(match, nickname);

        analyzer.logPlayerKills(logPlayerKillV2s);
        Set<String> victims = analyzer.getVictimNames();
        Set<String> member = roster.extractParticipantName();

        List<LogPlayerTakeDamage> rosterLogPlayerTakeDamages = telemetryService.findDamageLogs(match, victims, member);
        analyzer.rosterLogPlayerTakeDamages(rosterLogPlayerTakeDamages);

        List<LogPlayerTakeDamage> totalLogPlayerTakeDamages = telemetryService.findDamageLogs(match, nickname);
        analyzer.totalLogPlayerTakeDamage(totalLogPlayerTakeDamages);

        return ReportRes.of(
                MatchInfoRes.from(match),
                MatchResultRes.of(match, roster, participant),
                AnalyzerRes.of(analyzer)
        );
    }
}
