package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.lib.Match.Match;
import com.menu.pubganalyzer.lib.Telemetry.LogPlayerKillList;
import com.menu.pubganalyzer.lib.Telemetry.LogPlayerTakeDamageList;
import com.menu.pubganalyzer.lib.Telemetry.Telemetry;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalysisService {
    @Autowired
    private MatchesService matchesService;
    @Autowired
    private TelemetryService telemetryService;

    public String killAndDamage(String matchId, String[] playerNames) {
        JSONObject playerTotalDamage = new JSONObject();
        // 매치 데이터 읽기
        try {
            Match match = matchesService.getMatch(matchId);
            Telemetry telemetry = telemetryService.getTelemetry(matchId);
            LogPlayerTakeDamageList logPlayerTakeDamageList = telemetry.getLogPlayerTakeDamageList();
            LogPlayerKillList logPlayerKillList = telemetry.getLogPlayerKillList();
            Map<String, List<String>> playersKillLog =  new HashMap<>();

            for (int i = 0; i < playerNames.length; i++) {
                LogPlayerKillList playerKillLog = logPlayerKillList.getByKiller(playerNames[i]);
                List<String> victims = new ArrayList<>();
                for (int j = 0; j < playerKillLog.size(); j++) {
                    victims.add(playerKillLog.get(j).getVictim().getName());
                }
                playersKillLog.put(playerNames[i], victims);
            }

            for (int i = 0; i < playersKillLog.size(); i++) {
                String player = playerNames[i];
                List<String> victims = playersKillLog.get(player); // player의 killLog 저장
                JSONObject victimJSON = new JSONObject();
                for (int j = 0; j < victims.size(); j++) { // victim 반복
                    JSONObject damageJSON = new JSONObject();
                    String victim = victims.get(j);
                    for (int k = 0; k < playerNames.length; k++) { // player(attacker) 반복
                        JSONObject damageData = new JSONObject();
                        String attacker = playerNames[k];
                        LogPlayerTakeDamageList damageToVictimFromAttacker = logPlayerTakeDamageList.get(attacker, victim);
                        damageData.put("totalDamage", damageToVictimFromAttacker.getTotalDamage());
                        damageData.put("data", damageToVictimFromAttacker.toJSON());
                        damageJSON.put(attacker, damageData);
                    }
                    victimJSON.put(victim, damageJSON);
                }
                playerTotalDamage.put(player, victimJSON);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return playerTotalDamage.toString();
    }
}
