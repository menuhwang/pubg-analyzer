package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameStateResponse {
    private int elapsedTime;
    private int numAliveTeams;
    private int numJoinPlayers;
    private int numStartPlayers;
    private int numStartTeams;
    private int numParticipatedTeams;
    private int numParticipatedPlayers;
    private int numAlivePlayers;
    private LocationResponse safetyZonePosition;
    private double safetyZoneRadius;
    private LocationResponse poisonGasWarningPosition;
    private double poisonGasWarningRadius;
    private LocationResponse redZonePosition;
    private double redZoneRadius;
    private LocationResponse blackZonePosition;
    private double blackZoneRadius;

    public static GameStateResponse mappedBy(Object o) {
        if (Objects.isNull(o))
            return null;

        if (!(o instanceof Map)) {
            throw new IllegalArgumentException("Failed to map to GameStateResponse. Object is not of Map type.");
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) o;

        try {
            int elapsedTime = (int) map.get("elapsedTime");
            int numAliveTeams = (int) map.get("numAliveTeams");
            int numJoinPlayers = (int) map.get("numJoinPlayers");
            int numStartPlayers = (int) map.get("numStartPlayers");
            int numStartTeams = (int) map.get("numStartTeams");
            int numParticipatedTeams = (int) map.get("numParticipatedTeams");
            int numParticipatedPlayers = (int) map.get("numParticipatedPlayers");
            int numAlivePlayers = (int) map.get("numAlivePlayers");

            LocationResponse safetyZonePosition = LocationResponse.mappedBy(map.get("safetyZonePosition"));
            double safetyZoneRadius = ((Number) map.get("safetyZoneRadius")).doubleValue();

            LocationResponse poisonGasWarningPosition = LocationResponse.mappedBy(map.get("poisonGasWarningPosition"));
            double poisonGasWarningRadius = ((Number) map.get("poisonGasWarningRadius")).doubleValue();

            LocationResponse redZonePosition = LocationResponse.mappedBy(map.get("redZonePosition"));
            double redZoneRadius = ((Number) map.get("redZoneRadius")).doubleValue();

            LocationResponse blackZonePosition = LocationResponse.mappedBy(map.get("blackZonePosition"));
            double blackZoneRadius = ((Number) map.get("blackZoneRadius")).doubleValue();

            return GameStateResponse.builder()
                    .elapsedTime(elapsedTime)
                    .numAliveTeams(numAliveTeams)
                    .numJoinPlayers(numJoinPlayers)
                    .numStartPlayers(numStartPlayers)
                    .numStartTeams(numStartTeams)
                    .numParticipatedTeams(numParticipatedTeams)
                    .numParticipatedPlayers(numParticipatedPlayers)
                    .numAlivePlayers(numAlivePlayers)
                    .safetyZonePosition(safetyZonePosition)
                    .safetyZoneRadius(safetyZoneRadius)
                    .poisonGasWarningPosition(poisonGasWarningPosition)
                    .poisonGasWarningRadius(poisonGasWarningRadius)
                    .redZonePosition(redZonePosition)
                    .redZoneRadius(redZoneRadius)
                    .blackZonePosition(blackZonePosition)
                    .blackZoneRadius(blackZoneRadius)
                    .build();
        } catch (ClassCastException e) {
            System.err.println(map);
            throw e;
        }
    }

}
