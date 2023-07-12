package com.menu.pubganalyzer.domain.model;

import com.menu.pubganalyzer.exception.ParticipantNotFoundException;
import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

/*
CREATE TABLE matches
(
    `id`              CHAR(36)  NOT NULL,
    `game_mode`       VARCHAR(255) NULL,
    `season_state`    VARCHAR(255) NULL,
    `duration`        INT          NOT NULL,
    `title_id`        VARCHAR(255) NULL,
    `shard_id`        VARCHAR(255) NULL,
    `map_name`        VARCHAR(255) NULL,
    `is_custom_match` BIT(1)       NOT NULL,
    `match_type`      VARCHAR(255) NULL,
    `created_at`      datetime     NULL,
    `asset_id`        CHAR(36)  NULL,
    CONSTRAINT pk_matches PRIMARY KEY (id)
);

CREATE INDEX match_id_shard_index ON matches (id, shard_id);

ALTER TABLE matches
ADD CONSTRAINT FK_MATCHES_ON_ASSET FOREIGN KEY (asset_id) REFERENCES asset (id);
*/

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
    @Column(length = 36)
    private String id;
    private String gameMode;
    private String seasonState;
    private int duration;
    private String titleId;
    private String shardId;
    private String mapName;
    private boolean isCustomMatch;
    private String matchType;
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
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

    public void setRosters(Collection<Roster> rosters) {
        rosters.forEach(roster -> roster.setMatch(this));
        this.rosters = new HashSet<>(rosters);
    }

    public Set<String> extractPlayerNames() {
        Set<String> names = new HashSet<>();
        for (Roster roster : rosters) {
            for (Participant participant : roster.getParticipants()) {
                names.add(participant.getName());
            }
        }
        return names;
    }

    public static Match of(MatchResponse matchResponse) {
        Match m = Match.builder()
                .id(matchResponse.getId())
                .gameMode(matchResponse.getAttributes().getGameMode().toUpperCase())
                .seasonState(matchResponse.getAttributes().getSeasonState().toUpperCase())
                .duration(matchResponse.getAttributes().getDuration())
                .titleId(matchResponse.getAttributes().getTitleId())
                .shardId(matchResponse.getAttributes().getShardId().toUpperCase())
                .mapName(matchResponse.getAttributes().getMapName().toUpperCase())
                .isCustomMatch(matchResponse.getAttributes().getIsCustomMatch())
                .matchType(matchResponse.getAttributes().getMatchType().toUpperCase())
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
