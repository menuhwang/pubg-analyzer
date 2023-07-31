package com.menu.pubganalyzer.domain.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.menu.pubganalyzer.domain.model.telemetries.Telemetry;
import com.menu.pubganalyzer.exception.ServerException;
import com.menu.pubganalyzer.util.filemanager.FileManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TelemetryRepositoryImpl implements TelemetryRepository {
    private final static String LOG_PLAYER_KILL_V2 = "LogPlayerKillV2";
    private final static String LOG_PLAYER_TAKE_DAMAGE = "LogPlayerTakeDamage";
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private final FileManager telemetryFileManager;

    @Override
    public boolean existsByMatchId(String matchId) {
        return telemetryFileManager.exists(matchId + ".json");
    }

    @Override
    public List<Telemetry> findLogPlayerKillByMatchIdAndPlayerName(String matchId, String playerName) {
        List<Telemetry> telemetries = readAsTelemetries(matchId);
        return telemetries.stream()
                    .filter(telemetry -> telemetry.getType().equals(LOG_PLAYER_KILL_V2))
                    .filter(telemetry -> filterByKillerName(telemetry, playerName))
                    .collect(Collectors.toList());
    }

    private boolean filterByKillerName(Telemetry telemetry, String playerName) {
        Map<String, Object> attribute = telemetry.getAttribute();
        if (!attribute.containsKey("killer")) return false;
        Map<String, Object> character = (Map<String, Object>) attribute.get("killer");
        return character.get("name").equals(playerName);
    }

    @Override
    public List<Telemetry> findLogPlayerTakeDamageByVictimsAndAttacker(String matchId, Collection<String> victims, Collection<String> attackers) {
        List<Telemetry> telemetries = readAsTelemetries(matchId);
        return telemetries.stream()
                .filter(telemetry -> telemetry.getType().equals(LOG_PLAYER_TAKE_DAMAGE))
                .filter(telemetry -> filterByVictims(telemetry, victims))
                .filter(telemetry -> filterByAttackers(telemetry, attackers))
                .collect(Collectors.toList());
    }

    private boolean filterByVictims(Telemetry telemetry, Collection<String> victims) {
        Map<String, Object> attribute = telemetry.getAttribute();
        if (!attribute.containsKey("victim")) return false;
        Map<String, Object> character = (Map<String, Object>) attribute.get("victim");
        return victims.contains((String) character.get("name"));
    }

    private boolean filterByAttackers(Telemetry telemetry, Collection<String> attackers) {
        Map<String, Object> attribute = telemetry.getAttribute();
        if (!attribute.containsKey("attacker")) return false;
        Map<String, Object> character = (Map<String, Object>) attribute.get("attacker");
        return attackers.contains((String) character.get("name"));
    }

    @Override
    public List<Telemetry> findLogPlayerTakeDamageByAttacker(String matchId, String attacker) {
        List<Telemetry> telemetries = readAsTelemetries(matchId);
        return telemetries.stream()
                .filter(telemetry -> telemetry.getType().equals(LOG_PLAYER_TAKE_DAMAGE))
                .filter(telemetry -> filterByAttackerName(telemetry, attacker))
                .filter(this::filterByVictimEqualAttacker)
                .collect(Collectors.toList());
    }

    private boolean filterByAttackerName(Telemetry telemetry, String playerName) {
        Map<String, Object> attribute = telemetry.getAttribute();
        if (!attribute.containsKey("attacker")) return false;
        Map<String, Object> character = (Map<String, Object>) attribute.get("attacker");
        return character.get("name").equals(playerName);
    }

    private boolean filterByVictimEqualAttacker(Telemetry telemetry) {
        Map<String, Object> attribute = telemetry.getAttribute();
        if (!attribute.containsKey("victim")) return false;
        if (!attribute.containsKey("attacker")) return false;
        Map<String, Object> victim = (Map<String, Object>) attribute.get("victim");
        Map<String, Object> attacker = (Map<String, Object>) attribute.get("attacker");
        return victim.get("name").equals(attacker.get("name"));
    }

    @Override
    public void saveAll(String filename, Collection<Telemetry> telemetries) {
        try {
            String json = objectMapper.writeValueAsString(telemetries);
            telemetryFileManager.saveJson(filename, json);
        } catch (JsonProcessingException e) {
            throw new ServerException(e);
        }
    }

    private List<Telemetry> readAsTelemetries(String matchId) {
        String json = telemetryFileManager.readJson(matchId);
        List<Telemetry> telemetries = Collections.emptyList();
        try {
            telemetries = objectMapper.readValue(json, new TypeReference<List<Telemetry>>() {});
        } catch (Exception e) {
            throw new ServerException(e);
        }
        return telemetries;
    }
}
