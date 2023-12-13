package com.menu.pubganalyzer.util.pubg.response.match;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Attribute {
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
