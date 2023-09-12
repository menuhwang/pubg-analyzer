package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.dto.DamageLogRes;
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


    public List<LogPlayerKillV2> findKillLogs(Match match, String playerName) {
        return telemetryRepository.findLogPlayerKillByMatchIdAndPlayerName(match.getId(), playerName).stream()
                .map(LogPlayerKillV2Impl::new)
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

    public boolean existsTelemetry(Match match) {
        return telemetryRepository.existsByMatchId(match.getId());
    }

    public void fetchTelemetry(Match match) {
        List<Telemetry> telemetries = pubgService.fetchTelemetry(match);

        telemetryRepository.saveAll(match.getId(), telemetries);
    }
}
