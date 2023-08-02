package com.menu.pubganalyzer.util.pubgAPI.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Delegate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"meta"})
public class MatchResponse {
    @Delegate
    private Data data;
    private Link links;
    private List<Element> included;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
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
        String schema;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
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
        @JsonProperty(value = "URL")
        String URL;

        public String getShardId() {
            return shardId == null ? null : shardId.toUpperCase();
        }

        public String getGameMode() {
            return gameMode == null ? null : gameMode.replaceAll("-", "_").toUpperCase();
        }

        public String getSeasonState() {
            return seasonState == null ? null : seasonState.toUpperCase();
        }

        public String getTitleId() {
            return titleId == null ? null : titleId.toUpperCase();
        }

        public String getMapName() {
            return mapName == null ? null : mapName.toUpperCase();
        }

        public String getMatchType() {
            return matchType == null ? null : matchType.toUpperCase();
        }
    }

    @Getter
    @ToString
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
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

        public String getDeathType() {
            return deathType == null ? null : deathType.toUpperCase();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    public static class Relationship {
        Included rosters;
        Included assets;
        Included participants;
        Included team;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @JsonIgnoreProperties({"empty"})
    public static class Included {
        @Delegate
        List<Element> data;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    public static class Element {
        String type;
        String id;
        Attribute attributes;
        Relationship relationships;
    }
}
