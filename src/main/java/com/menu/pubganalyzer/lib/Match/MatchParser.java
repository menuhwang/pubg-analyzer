package com.menu.pubganalyzer.lib.Match;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class MatchParser {
    private JSONObject json;
    private JSONObject asset;
    private JSONObject attributes;
    private List<Roster> rosters;
    private List<Participant> participants;

    public MatchParser(String raw) {
        try {
            this.json = new JSONObject(raw);
//            this.attributes = this.parseMatchAttributes();
//            this.parseIncluded();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject parseMatchAttributes() {
        JSONObject attributes = null;
        try {
            attributes = this.json.getJSONObject("data").getJSONObject("attributes");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return attributes;
    }

    private void parseIncluded() {
        this.asset = new JSONObject();
        this.rosters = new ArrayList<>();
        this.participants = new ArrayList<>();
        try{
            JSONArray  included = this.json.getJSONArray("included") ;
            for (int i = 0; i < included.length(); i++) {
                JSONObject item = included.getJSONObject(i);
                String type = item.getString("type");
                switch (type) {
                    case "asset" -> this.asset = item.getJSONObject("attributes");
                    case "participant" -> this.participants.add(new Participant(item));
                    case "roster" -> this.rosters.add(new Roster(item));
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    public String getId() {
        String id = null;
        try {
            id = this.json.getJSONObject("data").getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return id;
    }

    public String getGameMode() {
        String gameMode = null;
        try {
            gameMode = this.attributes.getString("gameMode");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return gameMode;
    }

    public String getMapName() {
        String mapName = null;
        try {
            mapName = this.attributes.getString("mapName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mapName;
    }

    public String getTelemetryURL() {
        String telemetryURL = null;
        try {
            telemetryURL = this.asset.getString("URL");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return telemetryURL;
    }
    public List<Roster> getRosters() {
        return this.rosters;
    }
    public List<Participant> getParticipants() {
        return this.participants;
    }
    public String getCreatedAt() {
        String createdAt;
        try {
            createdAt = this.attributes.getString("createdAt");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return createdAt;
    }
}
