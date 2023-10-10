package com.menu.pubganalyzer.util.pubgAPI.response.match;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Stat {
    @JsonProperty(value = "DBNOs")
    Integer DBNOs;
    Integer assists;
    Integer boosts;
    Float damageDealt;
    String deathType;
    Integer headshotKills;
    Integer heals;
    Integer killPlace;
    Integer killStreaks;
    Integer kills;
    Float longestKill;
    String name;
    String playerId;
    Integer revives;
    Float rideDistance;
    Integer roadKills;
    Float swimDistance;
    Integer teamKills;
    Float timeSurvived;
    Integer vehicleDestroys;
    Float walkDistance;
    Integer weaponsAcquired;
    Integer winPlace;
    Integer rank;
    Integer teamId;

    public String getDeathType() {
        return deathType == null ? null : deathType.toUpperCase();
    }
}
