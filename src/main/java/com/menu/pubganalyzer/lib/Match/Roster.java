package com.menu.pubganalyzer.lib.Match;

import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;

@Getter
public class Roster {
    private String id;
    private int rank;
    private int teamId;
    private boolean won;

    public Roster(JSONObject roster) {
        try {
            this.id = roster.getString("id");
            this.rank = roster.getJSONObject("attributes").getJSONObject("stats").getInt("rank");
            this.teamId = roster.getJSONObject("attributes").getJSONObject("stats").getInt("teamId");
            this.won = roster.getJSONObject("attributes").getBoolean("won");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
