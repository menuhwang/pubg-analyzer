package com.menu.pubganalyzer.lib.Telemetry;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class LogPlayerKillList {
    private List<LogPlayerKill> list;
    public LogPlayerKillList(List<LogPlayerKill> list) {
        this.list = list;
    }
    public LogPlayerKillList(JSONArray list) {
        try {
            List<LogPlayerKill> newList = new ArrayList<>();
            for (int i = 0; i < list.length(); i++) {
                newList.add(new LogPlayerKill(list.getJSONObject(i)));
            }
            this.list = newList;
        } catch (JSONException e) {
            System.out.println("LogPlayerKillList 초기화 오류 [JSON 파싱 오류]");
        }
    }
    public LogPlayerKillList getByKiller(String killer) {
        List<LogPlayerKill> toReturn = new ArrayList<>();
        for (int i = 0; i < this.list.size(); i++) {
            LogPlayerKill killLog = this.list.get(i);
            try {
                if (killer.equals(killLog.getKiller().getName())) {
                    toReturn.add(killLog);
                }
            } catch (NullPointerException e) {
                continue;
            }
        }
        return new LogPlayerKillList(toReturn);
    }
    public JSONArray toJSON() {
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < this.list.size(); i++) {
            jsonArray.put(this.list.get(i).toJSON());
        }

        return jsonArray;
    }
    public LogPlayerKill get(int index) {
        return this.list.get(index);
    }
    public int size() {
        return this.list.size();
    }
}
