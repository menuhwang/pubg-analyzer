package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GameStateResponse {
    private int elapsedTime;
    private int numAliveTeams;
    private int numJoinPlayers;
    private int numStartPlayers;
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
