package com.menu.pubganalyzer.lib.Telemetry;

import lombok.Getter;
import lombok.ToString;
import org.json.JSONException;
import org.json.JSONObject;

@Getter
@ToString
public class LogPlayerTakeDamage {
    private String timestamp;
    private Character attacker;
    private Character victim;
    private String damageType;
    private double damage;
    private String damageReason;
    private String damageTypeCategory;

    public LogPlayerTakeDamage(JSONObject log) {
        try {
            this.timestamp = log.getString("_D");
            this.attacker = new Character(log.getJSONObject("attacker"));
            this.victim = new Character(log.getJSONObject("victim"));
            this.damageType = log.getString("damageTypeCategory");
            this.damage = log.getDouble("damage");
            this.damageReason = log.getString("damageReason");
            this.damageTypeCategory = log.getString("damageTypeCategory");
        } catch (JSONException e) {
            System.out.println(log);
            e.printStackTrace();
        }
    }
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("timestamp", this.timestamp);
            jsonObject.put("attacker", this.attacker.toJSON());
            jsonObject.put("victim", this.victim.toJSON());
            jsonObject.put("damageType", this.damageType);
            jsonObject.put("damage", this.damage);
            jsonObject.put("damageReason", this.damageReason);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
