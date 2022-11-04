package com.menu.pubganalyzer.lib.Telemetry;

import lombok.Getter;
import lombok.ToString;
import org.json.JSONException;
import org.json.JSONObject;

@Getter
@ToString
public class LogPlayerKill {
    private String timestamp;
    private Character killer;
    private Character victim;

    public LogPlayerKill(JSONObject log) {
        try {
            this.timestamp = log.getString("_D");
            this.killer = new Character(log.getJSONObject("killer"));
            this.victim = new Character(log.getJSONObject("victim"));
        } catch (JSONException e) {
            System.out.println(log);
            e.printStackTrace();
        }
    }
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("timestamp", this.timestamp);
            jsonObject.put("killer", this.killer.toJSON());
            jsonObject.put("victim", this.victim.toJSON());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}