package com.menu.pubganalyzer.model;

import com.menu.pubganalyzer.model.enums.Shard;
import com.menu.pubganalyzer.model.enums.match.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
public class Match {
    private String id;
    private GameMode gameMode;
    private SeasonState seasonState;
    private int duration;
    private String titleId;
    private Shard shardId;
    private MapName mapName;
    private boolean isCustomMatch;
    private MatchType matchType;
    private LocalDateTime createdAt;

    private Asset asset; //Entity ignore
    private Set<Participant> participants; //Entity ignore
    private Set<Roster> rosters; //Entity ignore

    private Match() {
    }

    @Builder
    private Match(String id, GameMode gameMode, SeasonState seasonState, int duration, String titleId, Shard shardId, MapName mapName, boolean isCustomMatch, MatchType matchType, LocalDateTime createdAt) {
        this.id = id;
        this.gameMode = gameMode;
        this.seasonState = seasonState;
        this.duration = duration;
        this.titleId = titleId;
        this.shardId = shardId;
        this.mapName = mapName;
        this.isCustomMatch = isCustomMatch;
        this.matchType = matchType;
        this.createdAt = createdAt;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public void setParticipants(Collection<Participant> participants) {
        this.participants = new HashSet<>(participants);
    }

    public void setRosters(Collection<Roster> rosters) {
        this.rosters = new HashSet<>(rosters);
    }

    public static Match of(Map<String, Object> raw) {
        String type = (String) raw.getOrDefault("type", null);
        if (type == null || !type.equals("match")) throw new IllegalArgumentException("정상적인 매치 데이터를 입력해주세요.");

        Map<String, Object> attributes = (Map<String, Object>) raw.get("attributes");

        return Match.builder()
                .id((String) raw.get("id"))
                .gameMode(GameMode.of((String) attributes.get("gameMode")))
                .seasonState(SeasonState.valueOf(((String) attributes.get("seasonState")).toUpperCase()))
                .duration((int) attributes.get("duration"))
                .titleId((String) attributes.get("titleId"))
                .shardId(Shard.valueOf(((String) attributes.get("shardId")).toUpperCase()))
                .mapName(MapName.valueOf((String) attributes.get("mapName")))
                .isCustomMatch((boolean) attributes.get("isCustomMatch"))
                .matchType(MatchType.valueOf(((String) attributes.get("matchType")).toUpperCase()))
                .createdAt(LocalDateTime.parse((String) attributes.get("createdAt"), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")))
                .build();
    }

    @Override
    public String toString() {
        return "Match{" +
                "id='" + id + '\'' +
                ", gameMode=" + gameMode +
                ", seasonState=" + seasonState +
                ", duration=" + duration +
                ", titleId='" + titleId + '\'' +
                ", shardId=" + shardId +
                ", mapName=" + mapName +
                ", isCustomMatch=" + isCustomMatch +
                ", matchType=" + matchType +
                ", createdAt=" + createdAt +
                '}';
    }
}
