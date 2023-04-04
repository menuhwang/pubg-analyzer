package com.menu.pubganalyzer.domain.model;

import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.domain.model.enums.match.GameMode;
import com.menu.pubganalyzer.domain.model.enums.match.MapName;
import com.menu.pubganalyzer.domain.model.enums.match.MatchType;
import com.menu.pubganalyzer.domain.model.enums.match.SeasonState;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Entity
@Table(name = "matches",
        indexes = {
        @Index(name = "match_id_shard_index", columnList = "id, shardId")
})
public class Match {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private GameMode gameMode;
    @Enumerated(EnumType.STRING)
    private SeasonState seasonState;
    private int duration;
    private String titleId;
    @Enumerated(EnumType.STRING)
    private Shard shardId;
    @Enumerated(EnumType.STRING)
    private MapName mapName;
    private boolean isCustomMatch;
    @Enumerated(EnumType.STRING)
    private MatchType matchType;
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "match", cascade = {CascadeType.ALL})
    private Asset asset;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "match", cascade = {CascadeType.ALL})
    @OrderBy("winPlace")
    private Set<Participant> participants = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "match", cascade = {CascadeType.ALL})
    @OrderBy("rank")
    private Set<Roster> rosters = new HashSet<>();

    protected Match() {
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

    public Participant getParticipant(Player player) {
        return getParticipants().stream()
                .filter(p -> p.getPlayerId().equals(player.getId()))
                .findFirst().orElse(null);
    }

    public Participant getParticipant(String playerId) {
        return getParticipants().stream()
                .filter(p -> p.getPlayerId().equals(playerId))
                .findFirst().orElse(null);
    }

    public String getZonedCreatedAt() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(this.createdAt, ZoneId.of("UTC"));
        ZonedDateTime createdAtKor = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Seoul"));
        return createdAtKor.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

    public String getCreatedOffset() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        System.out.println(now);
        long offset = ChronoUnit.MINUTES.between(this.createdAt, now);
        if (offset < 60) return offset + "분 전";
        if (offset < (24 * 60)) return offset / 60 + "시간 전";
        if (offset < (30 * 24 * 60)) return offset / (24 * 60) + "일 전";
        if (offset < (12 * 30 * 24 * 60)) return offset / (30 * 24 * 60) + "달 전";
        return offset / 518400 + "년 전";
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
