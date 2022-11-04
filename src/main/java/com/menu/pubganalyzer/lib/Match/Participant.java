package com.menu.pubganalyzer.lib.Match;

import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;

@Getter
public class Participant {
    private String id;
    private int assists;
    private double damageDealt;
    private int kills;
    private String name;
    private String playerId;
    private int winPlace;

    public Participant(JSONObject participant) {
        try {
            JSONObject stats = participant.getJSONObject("attributes").getJSONObject("stats");
            this.id = participant.getString("id");
            this.assists = stats.getInt("assists");
            this.damageDealt = stats.getDouble("damageDealt");
            this.kills = stats.getInt("kills");
            this.name = stats.getString("name");
            this.playerId = stats.getString("playerId");
            this.winPlace = stats.getInt("winPlace");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        JSONObject participant = new JSONObject();
        try {
            participant.put("id", this.id);
            participant.put("assists", this.assists);
            participant.put("damageDealt", this.damageDealt);
            participant.put("kills", this.kills);
            participant.put("name", this.name);
            participant.put("playerId", this.playerId);
            participant.put("winPlace", this.winPlace);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return participant.toString();
    }
}
