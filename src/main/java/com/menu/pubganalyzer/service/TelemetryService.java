package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.dto.ContributeDamageChartRes;
import com.menu.pubganalyzer.domain.dto.DamageLogRes;
import com.menu.pubganalyzer.domain.dto.KillLogRes;
import com.menu.pubganalyzer.domain.dto.PhaseDamageChartRes;
import com.menu.pubganalyzer.domain.model.matches.Match;
import com.menu.pubganalyzer.domain.model.matches.Roster;
import com.menu.pubganalyzer.domain.model.telemetries.LogPlayerKillV2;
import com.menu.pubganalyzer.domain.model.telemetries.LogPlayerTakeDamage;
import com.menu.pubganalyzer.domain.model.telemetries.Telemetry;
import com.menu.pubganalyzer.domain.model.telemetries.impl.LogPlayerKillV2Impl;
import com.menu.pubganalyzer.domain.model.telemetries.impl.LogPlayerTakeDamageImpl;
import com.menu.pubganalyzer.domain.repository.MatchRepository;
import com.menu.pubganalyzer.domain.repository.TelemetryRepository;
import com.menu.pubganalyzer.exception.MatchNotFoundException;
import com.menu.pubganalyzer.util.ChartUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TelemetryService {
    private final PubgService pubgService;
    private final MatchRepository matchRepository;
    private final TelemetryRepository telemetryRepository;

    public List<KillLogRes> findKillLogs(
            final String id,
            final String playerName) {
        return telemetryRepository.findLogPlayerKillByMatchIdAndPlayerName(id, playerName).stream()
                .map(LogPlayerKillV2Impl::new)
                .map(KillLogRes::of)
                .collect(Collectors.toList());
    }

    public List<LogPlayerTakeDamage> findDamageLogs(Match match, Collection<String> victims, Collection<String> attackers) {
        return telemetryRepository.findLogPlayerTakeDamageByVictimsAndAttacker(match.getId(), victims, attackers).stream()
                .map(LogPlayerTakeDamageImpl::new)
                .collect(Collectors.toList());
    }

    public List<LogPlayerTakeDamage> findDamageLogs(Match match, String attacker) {
        return telemetryRepository.findLogPlayerTakeDamageByAttacker(match.getId(), attacker).stream()
                .map(LogPlayerTakeDamageImpl::new)
                .collect(Collectors.toList());
    }

    public List<DamageLogRes> findDamagesOfKill(
            final String id,
            final String playerName) {
        Match match = matchRepository.findById(id)
                .orElseThrow(MatchNotFoundException::new);
        if (!telemetryRepository.existsByMatchId(id)) {
            List<Telemetry> telemetries = pubgService.fetchTelemetry(match);
            telemetryRepository.saveAll(id, telemetries);
        }

        Set<String> victims = telemetryRepository.findLogPlayerKillByMatchIdAndPlayerName(id, playerName).stream()
                .map(LogPlayerKillV2Impl::new)
                .map(LogPlayerKillV2::getVictimName)
                .collect(Collectors.toSet());

        Roster roster = match.getRosterByName(playerName);


        return telemetryRepository.findLogPlayerTakeDamageByVictimsAndAttacker(id, victims, roster.extractParticipantName()).stream()
                .map(LogPlayerTakeDamageImpl::new)
                .map(DamageLogRes::of)
                .collect(Collectors.toList());
    }

    public List<DamageLogRes> findDamageLogByPlayer(
            final String id,
            final String playerName) {
        return telemetryRepository.findLogPlayerTakeDamageByAttacker(id, playerName).stream()
                .map(LogPlayerTakeDamageImpl::new)
                .map(DamageLogRes::of)
                .collect(Collectors.toList());
    }

    public PhaseDamageChartRes getPhaseDamageChart(
            final String id,
            final String playerName) {
        List<LogPlayerTakeDamage> logPlayerTakeDamages = telemetryRepository.findLogPlayerTakeDamageByAttacker(id, playerName).stream()
                .map(LogPlayerTakeDamageImpl::new)
                .collect(Collectors.toList());

        return ChartUtil.phaseDamageChart(logPlayerTakeDamages);
    }

    public ContributeDamageChartRes getContributeDamageChart(
            final String id,
            final String playerName) {
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

    public boolean existsTelemetry(Match match) {
        return telemetryRepository.existsByMatchId(match.getId());
    }

    public void fetchTelemetry(Match match) {
        List<Telemetry> telemetries = pubgService.fetchTelemetry(match);

        telemetryRepository.saveAll(match.getId(), telemetries);
    }
}
