package com.menu.pubganalyzer.domain.repository;

import com.menu.pubganalyzer.domain.model.telemetries.Telemetry;

import java.util.Collection;
import java.util.List;

public interface TelemetryRepository {
    boolean existsByMatchId(String matchId);
    List<Telemetry> findLogPlayerKillByMatchIdAndPlayerName(String matchId, String playerName);
    List<Telemetry> findLogPlayerTakeDamageByVictimsAndAttacker(String matchId, Collection<String> victims, Collection<String> attackers);
    List<Telemetry> findLogPlayerTakeDamageByAttacker(String matchId, String attacker);
    void saveAll(String matchId, Collection<Telemetry> telemetries);
}
