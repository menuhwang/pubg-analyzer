package com.menu.pubganalyzer.lib.Telemetry;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.*;

public class LogPlayerTakeDamageList{
    private List<LogPlayerTakeDamage> list;
    public LogPlayerTakeDamageList(List<LogPlayerTakeDamage> list) {
        this.list = list;
    }
    public LogPlayerTakeDamageList(JSONArray list) {
        try {
            List<LogPlayerTakeDamage> newList = new ArrayList<>();
            for (int i = 0; i < list.length(); i++) {
                newList.add(new LogPlayerTakeDamage(list.getJSONObject(i)));
            }
            this.list = newList;
        } catch (JSONException e) {
            System.out.println("LogPlayerTakeDamageList 초기화 오류 [JSON 파싱 오류]");
        }
    }
    public double getTotalDamage() {
        double totalDamage = 0;
        for (int i = 0; i< this.list.size(); i++) {
            totalDamage += this.list.get(i).getDamage();
        }
        return totalDamage;
    }

    public LogPlayerTakeDamageList get(String attacker, String victim) {
        List<LogPlayerTakeDamage> buf = new ArrayList<>();
        for (int i = 0; i < this.list.size(); i++) {
            LogPlayerTakeDamage element = this.list.get(i);
            try {
                if (attacker.equals(element.getAttacker().getName()) && victim.equals(element.getVictim().getName())) {
                    buf.add(element);
                }
            } catch (NullPointerException e) {
                continue;
            }
        }
        return new LogPlayerTakeDamageList(buf);
    }
    public LogPlayerTakeDamageList getByAttacker(String attacker) {
        List<LogPlayerTakeDamage> buf = new ArrayList<>();
        for (int i = 0; i < this.list.size(); i++) {
            LogPlayerTakeDamage element = this.list.get(i);
            if (attacker.equals(element.getAttacker().getName())) {
                if ("Damage_Groggy".equals(element.getDamageTypeCategory())) {
                    continue;
                }
                buf.add(element);
            }
        }
        return new LogPlayerTakeDamageList(buf);
    }
    public LogPlayerTakeDamageList getByVictim(String victim) {
        List<LogPlayerTakeDamage> buf = new ArrayList<>();
        for (int i = 0; i < this.list.size(); i++) {
            LogPlayerTakeDamage element = this.list.get(i);
            if (victim.equals(element.getVictim().getName())) {
                buf.add(element);
            }
        }
        return new LogPlayerTakeDamageList(buf);
    }
    public JSONArray toJSON() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < this.list.size(); i++) {
            jsonArray.put(this.list.get(i).toJSON());
        }
        return jsonArray;
    }
    public int size() {
        return this.list.size();
    }
    public LogPlayerTakeDamage get(int index) {
        return this.list.get(index);
    }
}
