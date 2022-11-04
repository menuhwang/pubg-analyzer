package com.menu.pubganalyzer.lib.Telemetry;

import lombok.AccessLevel;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Getter
public class TelemetryParser {
    @Getter(AccessLevel.NONE)
    private String raw;
    private String logPlayerKillRaw;
    private String logPlayerTakeDamageRaw;
    private LogPlayerKillList logPlayerKillList;
    private LogPlayerTakeDamageList logPlayerTakeDamageList;

    public TelemetryParser(String raw) {
        this.raw = raw;
        sortLOG();
    }
    public TelemetryParser(String logPlayerKill, String logPlayerTakeDamage) {
        JSONArray logPlayerKillInit;
        JSONArray logPlayerTakeDamageInit;
        try {
            JSONArray sortedLogPlayerKillJSON = new JSONArray();
            JSONArray sortedLogPlayerTakeDamageJSON = new JSONArray();
            logPlayerKillInit = new JSONArray(logPlayerKill);
            logPlayerTakeDamageInit = new JSONArray(logPlayerTakeDamage);
            for (int i = 0; i < logPlayerKillInit.length(); i++) {
                JSONObject log = logPlayerKillInit.getJSONObject(i);
                sortedLogPlayerKillJSON.put(log);
            }
            for (int i = 0; i < logPlayerTakeDamageInit.length(); i++) {
                JSONObject log = logPlayerTakeDamageInit.getJSONObject(i);
                sortedLogPlayerTakeDamageJSON.put(log);
            }
            this.logPlayerKillRaw = sortedLogPlayerKillJSON.toString();
            this.logPlayerTakeDamageRaw = sortedLogPlayerTakeDamageJSON.toString();
            this.logPlayerKillList = new LogPlayerKillList(sortedLogPlayerKillJSON);
            this.logPlayerTakeDamageList = new LogPlayerTakeDamageList(sortedLogPlayerTakeDamageJSON);
        } catch (JSONException e) {
            System.out.println("텔레메트리 파서 초기화 오류 [JSON 파싱 오류]");
        }
    }
    private void sortLOG() {
        int debug = 0;
        try {
            JSONArray telemetry = new JSONArray(this.raw);
            JSONArray logPlayerKillJSON = new JSONArray();
            JSONArray logPlayerTakeDamageJSON = new JSONArray();
            for (int i = 0; i < telemetry.length(); i++) {
                JSONObject log = telemetry.getJSONObject(i);
                String logType = log.getString("_T");
                if (logType.equals("LogPlayerKillV2")) {
                    if (log.isNull("killer")) { // killer null 제외
                        continue;
                    }
                    if (!log.getString("teamKillers_AccountId").equals("[]")) { // 팀킬 제외
                        continue;
                    }
                    logPlayerKillJSON.put(log);
                } else if (logType.equals("LogPlayerTakeDamage")) {
                    if (log.isNull("attacker")) { // attacker null 제외
                        continue;
                    }
                    logPlayerTakeDamageJSON.put(log);
                }
                debug++;
            }
            this.logPlayerKillRaw = logPlayerKillJSON.toString();
            this.logPlayerTakeDamageRaw = logPlayerTakeDamageJSON.toString();
            this.logPlayerKillList = new LogPlayerKillList(logPlayerKillJSON);
            this.logPlayerTakeDamageList = new LogPlayerTakeDamageList(logPlayerTakeDamageJSON);
        } catch (JSONException e) {
            System.out.println(debug);
            e.printStackTrace();
        }
    }
}
