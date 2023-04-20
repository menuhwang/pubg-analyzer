package com.menu.pubganalyzer.util.pubgAPI.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Delegate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class MatchResponse {
    @Delegate
    private Data data;
    private Link links;
    private List<Element> included;

    @Getter
    @ToString
    public static class Data {
        String type;
        String id;
        Attribute attributes;
        Relationship relationships;
        Link links;
    }

    @Getter
    @ToString
    public static class Link {
        String self;
    }

    @Getter
    @ToString
    public static class Attribute {
        String gameMode;
        String seasonState;
        Stat stats;
        Integer duration;
        String titleId;
        String shardId;
        String tags;
        String mapName;
        Boolean isCustomMatch;
        String matchType;
        String actor;
        Boolean won;
        LocalDateTime createdAt;
        String name;
        String description;
        String URL;
    }

    @Getter
    @ToString
    public static class Stat {
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
    }

    @Getter
    @ToString
    public static class Relationship {
        Included rosters;
        Included assets;
        Included participants;
        Included team;
    }

    @Getter
    @ToString
    public static class Included {
        @Delegate
        List<Element> data;
    }

    @Getter
    @ToString
    public static class Element {
        String type;
        String id;
        Attribute attributes;
        Relationship relationships;
    }
}
