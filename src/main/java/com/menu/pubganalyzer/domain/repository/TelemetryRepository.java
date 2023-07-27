package com.menu.pubganalyzer.domain.repository;

import com.menu.pubganalyzer.domain.model.telemetries.Telemetry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.List;

public interface TelemetryRepository extends MongoRepository<Telemetry, String> {
    boolean existsByMatchId(String matchId);
    @Query(value = "{type: 'LogPlayerKillV2', matchId: ?0, 'attribute.killer.name': ?1}", sort = "{timestamp: 1}")
    List<Telemetry> findLogPlayerKillByMatchIdAndPlayerName(String matchId, String playerName);

    @Query(value = "{type: 'LogPlayerTakeDamage', matchId: ?0, 'attribute.attacker.name': ?1}", sort = "{timestamp: 1}")
    List<Telemetry> findLogPlayerTakeDamageByMatchIdAndPlayerName(String matchId, String playerName);

    @Query(value = "{type:  'LogPlayerTakeDamage', matchId:  ?0, 'attribute.victim.name': {'$in': ?1}, 'attribute.attacker.name': {'$in': ?2}}", sort = "{timestamp:  1}")
    List<Telemetry> findLogPlayerTakeDamageByVictimsAndAttacker(String matchId, Collection<String> victims, Collection<String> attackers);

    @Query(value = "{type:  'LogPlayerTakeDamage', matchId:  ?0, 'attribute.attacker.name': ?1, 'attribute.victim.name': {$ne: ?1}}", sort = "{timestamp: 1}")
    List<Telemetry> findLogPlayerTakeDamageByAttacker(String matchId, String attacker);
}
