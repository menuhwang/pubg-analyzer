package com.menu.pubganalyzer.lib.Telemetry;

import lombok.Getter;
import lombok.ToString;
import org.json.JSONException;
import org.json.JSONObject;

@Getter
@ToString
public class Character {
    private String name;
    private String accountId;
    private int teamId;
    private double health;

    public Character(JSONObject character) {
        try {
            if (character == null) {
                throw new NullPointerException();
            }
            this.name = character.getString("name");
            this.accountId = character.getString("accountId");
            this.teamId = character.getInt("teamId");
            this.health = character.getDouble("health");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", this.name);
            jsonObject.put("accountId", this.accountId);
            jsonObject.put("teamId", this.teamId);
            jsonObject.put("health", this.health);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
