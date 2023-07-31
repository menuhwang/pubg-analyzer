package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@Getter
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
    private float safetyZoneRadius;
    private LocationResponse poisonGasWarningPosition;
    private float poisonGasWarningRadius;
    private LocationResponse redZonePosition;
    private float redZoneRadius;
    private LocationResponse blackZonePosition;
    private float blackZoneRadius;
}
