package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.model.matches.Match;
import com.menu.pubganalyzer.domain.model.telemetries.LogPlayerKillV2;
import com.menu.pubganalyzer.domain.model.telemetries.LogPlayerTakeDamage;
import com.menu.pubganalyzer.domain.model.telemetries.Telemetry;
import com.menu.pubganalyzer.domain.model.telemetries.impl.LogPlayerKillV2Impl;
import com.menu.pubganalyzer.domain.model.telemetries.impl.LogPlayerTakeDamageImpl;
import com.menu.pubganalyzer.domain.repository.TelemetryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TelemetryService {
    private final PubgService pubgService;
    private final TelemetryRepository telemetryRepository;


    public List<LogPlayerKillV2> findKillLogs(Match match, String playerName){
        if(!existsTelemetry(match)) fetchTelemetry(match);

        return telemetryRepository.findLogPlayerKillByMatchIdAndPlayerName(match.getId(), playerName).stream()
                .map(LogPlayerKillV2Impl::new)
                .collect(Collectors.toList());
    }

    public List<LogPlayerTakeDamage> findDamageLogs(Match match, Collection<String> victims, Collection<String> member) {
        if(!existsTelemetry(match)) fetchTelemetry(match);

        return telemetryRepository.findLogPlayerTakeDamageByVictimsAndAttacker(match.getId(), victims, member).stream()
                .map(LogPlayerTakeDamageImpl::new)
                .collect(Collectors.toList());
    }

    public List<LogPlayerTakeDamage> findDamageLogs(Match match, String attackerName) {
        if(!existsTelemetry(match)) fetchTelemetry(match);

        return telemetryRepository.findLogPlayerTakeDamageByAttacker(match.getId(), attackerName).stream()
                .map(LogPlayerTakeDamageImpl::new)
                .collect(Collectors.toList());
    }

    private boolean existsTelemetry(Match match) {
        return telemetryRepository.existsByMatchId(match.getId());
    }

    private void fetchTelemetry(Match match) {
        List<Telemetry> telemetries = pubgService.fetchTelemetry(match);

        telemetryRepository.saveAll(match.getId(), telemetries);
    }
}
