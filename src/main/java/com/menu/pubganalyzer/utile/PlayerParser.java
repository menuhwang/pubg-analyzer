package com.menu.pubganalyzer.utile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlayerParser {
    private JSONArray data = new JSONArray();

    public PlayerParser(String raw) {
        try{
            JSONObject json = new JSONObject(raw);
            this.data = json.getJSONArray("data");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public String parseAccountId() {
        JSONArray accountIds = new JSONArray();
        try{
            for (int i = 0; i < this.data.length(); i++) {
                JSONObject element = new JSONObject();
                String name = null;
                String accountId = null;
                name  = this.data.getJSONObject(i).getJSONObject("attributes").getString("name");
                accountId = this.data.getJSONObject(i).getString("id");
                element.put("name", name);
                element.put("accountId", accountId);
                accountIds.put(element);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return accountIds.toString();
    }

    public String parseMatches() {
        JSONArray matchesIds = new JSONArray();
        try {
            for (int i = 0; i < this.data.length(); i++) {
                JSONObject element = new JSONObject();
                String name = null;
                List<String> matches = new ArrayList<>();
                JSONArray matchesData = this.data.getJSONObject(i).getJSONObject("relationships").getJSONObject("matches").getJSONArray("data");
                name  = this.data.getJSONObject(i).getJSONObject("attributes").getString("name");
                for (int j = 0; j < matchesData.length(); j++) {
                    matches.add(matchesData.getJSONObject(j).getString("id"));
                }
                element.put("name", name);
                element.put("matches", matches);
                matchesIds.put(element);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return matchesIds.toString();
    }
}
