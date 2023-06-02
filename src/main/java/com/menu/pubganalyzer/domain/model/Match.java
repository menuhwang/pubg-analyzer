package com.menu.pubganalyzer.domain.model;

import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.domain.model.enums.match.GameMode;
import com.menu.pubganalyzer.domain.model.enums.match.MapName;
import com.menu.pubganalyzer.domain.model.enums.match.MatchType;
import com.menu.pubganalyzer.domain.model.enums.match.SeasonState;
import com.menu.pubganalyzer.exception.ParticipantNotFoundException;
import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Getter
@Builder
@AllArgsConstructor
@Entity(name = "matches")
@Table(
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

    @OneToOne(cascade = {CascadeType.ALL})
    private Asset asset;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "match", cascade = {CascadeType.ALL})
    @OrderBy("rank")
    @Builder.Default
    private Set<Roster> rosters = new HashSet<>();

    protected Match() {
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Participant getParticipant(Player player) {
        for (Roster roster : this.getRosters()) {
            for (Participant participant : roster.getParticipants()) {
                if (participant.getName().equals(player.getName())) return participant;
            }
        }
        throw new ParticipantNotFoundException();
    }

    public Participant getParticipant(String playerName) {
        Player temp = Player.builder()
                .name(playerName)
                .build();
        return getParticipant(temp);
    }

    public String getZonedCreatedAt() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(this.createdAt, ZoneId.of("UTC"));
        ZonedDateTime createdAtKor = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Seoul"));
        return createdAtKor.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getCreatedOffset() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        long offset = ChronoUnit.MINUTES.between(this.createdAt, now);
        if (offset < 60) return offset + "분 전";
        if (offset < (24 * 60)) return offset / 60 + "시간 전";
        if (offset < (30 * 24 * 60)) return offset / (24 * 60) + "일 전";
        if (offset < (12 * 30 * 24 * 60)) return offset / (30 * 24 * 60) + "달 전";
        return offset / 518400 + "년 전";
    }

    public void setRosters(Collection<Roster> rosters) {
        rosters.forEach(roster -> roster.setMatch(this));
        this.rosters = new HashSet<>(rosters);
    }

    public static Match of(MatchResponse matchResponse) {
        Match m = Match.builder()
                .id(matchResponse.getId())
                .gameMode(GameMode.of(matchResponse.getAttributes().getGameMode()))
                .seasonState(SeasonState.valueOf((matchResponse.getAttributes().getSeasonState()).toUpperCase()))
                .duration(matchResponse.getAttributes().getDuration())
                .titleId(matchResponse.getAttributes().getTitleId())
                .shardId(Shard.valueOf((matchResponse.getAttributes().getShardId()).toUpperCase()))
                .mapName(MapName.valueOf(matchResponse.getAttributes().getMapName()))
                .isCustomMatch(matchResponse.getAttributes().getIsCustomMatch())
                .matchType(MatchType.valueOf((matchResponse.getAttributes().getMatchType()).toUpperCase()))
                .createdAt(matchResponse.getAttributes().getCreatedAt())
                .build();

        Map<String, Participant> participantMap = new HashMap<>();
        Map<String, Roster> rosterMap = new HashMap<>();

        matchResponse.getIncluded().forEach(element -> {
            switch (element.getType()) {
                case "participant":
                    participantMap.put(element.getId(), Participant.of(element));
                    break;
                case "roster":
                    rosterMap.put(element.getId(), Roster.of(element));
                    break;
                case "asset":
                    m.setAsset(Asset.of(element));
                    break;
                default:
                    break;
            }
        });

        m.setRosters(rosterMap.values());

        matchResponse.getIncluded().forEach(element -> {
            if (element.getType().equals("roster")) {
                Roster roster = rosterMap.get(element.getId());
                element.getRelationships().getParticipants().forEach(participant -> {
                    Participant participantModel = participantMap.get(participant.getId());
                    participantModel.setRoster(roster);
                    roster.addParticipant(participantModel);
                });
            }
        });

        return m;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Match match = (Match) o;
        return getId() != null && Objects.equals(getId(), match.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
