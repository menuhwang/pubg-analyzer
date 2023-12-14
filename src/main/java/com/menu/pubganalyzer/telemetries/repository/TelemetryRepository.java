package com.menu.pubganalyzer.telemetries.repository;

import com.menu.pubganalyzer.telemetries.model.TelemetryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.List;

public interface TelemetryRepository extends MongoRepository<TelemetryEntity, String> {
    boolean existsByMatchId(String matchId);
    @Query("{'matchId': ?0, 'telemetry.type': 'LogPlayerTakeDamage', 'telemetry.attacker.name': ?1, 'telemetry.victim.name':  {$ne: ?1}}")
    List<TelemetryEntity> findLogPlayerTakeDamageByAttacker(String matchId, String attacker);
    @Query("{'matchId': ?0, 'telemetry.type': 'LogPlayerKillV2', 'telemetry.killer.name': ?1}")
    List<TelemetryEntity> findLogPlayerKillByMatchIdAndPlayerName(String matchId, String playerName);
    @Query("{'matchId': ?0, 'telemetry.type': 'LogPlayerTakeDamage', 'telemetry.victim.name': {$in: ?1}, 'telemetry.attacker.name': {$in: ?2}}")
    List<TelemetryEntity> findLogPlayerTakeDamageByVictimsAndAttacker(String matchId, Collection<String> victims, Collection<String> attackers);
}
