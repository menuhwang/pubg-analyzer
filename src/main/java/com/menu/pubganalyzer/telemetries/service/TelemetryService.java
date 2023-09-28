package com.menu.pubganalyzer.telemetries.service;

import com.menu.pubganalyzer.telemetries.dto.response.ContributeDamageChartResponse;
import com.menu.pubganalyzer.telemetries.dto.response.DamageLogResponse;
import com.menu.pubganalyzer.telemetries.dto.response.KillLogResponse;
import com.menu.pubganalyzer.telemetries.dto.response.PhaseDamageChartResponse;
import com.menu.pubganalyzer.fetch.service.PubgService;
import com.menu.pubganalyzer.matches.model.Match;
import com.menu.pubganalyzer.matches.model.Roster;
import com.menu.pubganalyzer.telemetries.model.LogPlayerKillV2;
import com.menu.pubganalyzer.telemetries.model.LogPlayerTakeDamage;
import com.menu.pubganalyzer.telemetries.model.Telemetry;
import com.menu.pubganalyzer.telemetries.model.impl.LogPlayerKillV2Impl;
import com.menu.pubganalyzer.telemetries.model.impl.LogPlayerTakeDamageImpl;
import com.menu.pubganalyzer.matches.repository.MatchRepository;
import com.menu.pubganalyzer.common.exception.MatchNotFoundException;
import com.menu.pubganalyzer.telemetries.repository.TelemetryRepository;
import com.menu.pubganalyzer.util.ChartUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TelemetryService {
    private final PubgService pubgService;
    private final MatchRepository matchRepository;
    private final TelemetryRepository telemetryRepository;

    public List<KillLogResponse> findKillLogs(
            final String id,
            final String playerName) {
        fetchTelemetryIfAbsent(id);

        return telemetryRepository.findLogPlayerKillByMatchIdAndPlayerName(id, playerName).stream()
                .map(LogPlayerKillV2Impl::new)
                .map(KillLogResponse::of)
                .collect(Collectors.toList());
    }

    public List<DamageLogResponse> findDamagesOfKill(
            final String id,
            final String playerName) {
        fetchTelemetryIfAbsent(id);

        Match match = matchRepository.findById(id)
                .orElseThrow(MatchNotFoundException::new);

        Set<String> victims = telemetryRepository.findLogPlayerKillByMatchIdAndPlayerName(id, playerName).stream()
                .map(LogPlayerKillV2Impl::new)
                .map(LogPlayerKillV2::getVictimName)
                .collect(Collectors.toSet());

        Roster roster = match.getRosterByName(playerName);


        return telemetryRepository.findLogPlayerTakeDamageByVictimsAndAttacker(id, victims, roster.extractParticipantName()).stream()
                .map(LogPlayerTakeDamageImpl::new)
                .map(DamageLogResponse::of)
                .collect(Collectors.toList());
    }

    public List<DamageLogResponse> findDamageLogByPlayer(
            final String id,
            final String playerName) {
        fetchTelemetryIfAbsent(id);

        return telemetryRepository.findLogPlayerTakeDamageByAttacker(id, playerName).stream()
                .map(LogPlayerTakeDamageImpl::new)
                .map(DamageLogResponse::of)
                .collect(Collectors.toList());
    }

    public PhaseDamageChartResponse getPhaseDamageChart(
            final String id,
            final String playerName) {
        fetchTelemetryIfAbsent(id);

        List<LogPlayerTakeDamage> logPlayerTakeDamages = telemetryRepository.findLogPlayerTakeDamageByAttacker(id, playerName).stream()
                .map(LogPlayerTakeDamageImpl::new)
                .collect(Collectors.toList());

        return ChartUtil.phaseDamageChart(logPlayerTakeDamages);
    }

    public ContributeDamageChartResponse getContributeDamageChart(
            final String id,
            final String playerName) {
        fetchTelemetryIfAbsent(id);

        Match match = matchRepository.findById(id)
                .orElseThrow(MatchNotFoundException::new);

        Set<String> members = match.getRosterByName(playerName)
                .extractParticipantName();

        List<LogPlayerKillV2> logPlayerKills = telemetryRepository.findLogPlayerKillByMatchIdAndPlayerName(id, playerName).stream()
                .map(LogPlayerKillV2Impl::new)
                .collect(Collectors.toList());

        Set<String> victims = logPlayerKills.stream()
                .map(LogPlayerKillV2::getVictimName)
                .collect(Collectors.toSet());

        List<LogPlayerTakeDamage> logPlayerTakeDamages = telemetryRepository.findLogPlayerTakeDamageByVictimsAndAttacker(id, victims, members).stream()
                .map(LogPlayerTakeDamageImpl::new)
                .collect(Collectors.toList());

        return ChartUtil.contributeDamageChart(playerName, members, logPlayerKills, logPlayerTakeDamages);
    }

    private void fetchTelemetryIfAbsent(String id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(MatchNotFoundException::new);

        synchronized (this) {
            if (!telemetryRepository.existsByMatchId(id)) {
                List<Telemetry> telemetries = pubgService.fetchTelemetry(match);
                telemetryRepository.saveAll(id, telemetries);
            }
        }
    }
}
