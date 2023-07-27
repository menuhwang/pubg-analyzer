package com.menu.pubganalyzer.domain.model.matches;

import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.exception.ParticipantNotFoundException;
import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.Hibernate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Builder
@AllArgsConstructor
@Document(collection = "matches")
public class Match {
    @Id
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
    private Asset asset;
    @Builder.Default
    private List<Roster> rosters = new ArrayList<>();

    protected Match() {
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public void setRosters(Collection<Roster> rosters) {
        if (rosters instanceof List) {
            this.rosters = (List<Roster>) rosters;
            return;
        }
        this.rosters = new ArrayList<>(rosters);
    }

    public Participant getParticipantByName(String playerName) {
        return rosters.stream()
                .map(Roster::getParticipants)
                .flatMap(List::stream)
                .filter(participant -> participant.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("wrong player name."));
    }

    public Roster getRosterByName(String playerName) {
        return rosters.stream()
                .filter(roster -> roster.contains(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("wrong player name."));
    }

    public String getTelemetryUrl() {
        return asset.getUrl();
    }

    public static Match of(MatchResponse matchResponse) {
        Match m = Match.builder()
                .id(matchResponse.getId())
                .gameMode(matchResponse.getAttributes().getGameMode())
                .seasonState(matchResponse.getAttributes().getSeasonState())
                .duration(matchResponse.getAttributes().getDuration())
                .titleId(matchResponse.getAttributes().getTitleId())
                .shardId(matchResponse.getAttributes().getShardId())
                .mapName(matchResponse.getAttributes().getMapName())
                .isCustomMatch(matchResponse.getAttributes().getIsCustomMatch())
                .matchType(matchResponse.getAttributes().getMatchType())
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

        matchResponse.getIncluded().forEach(element -> {
            if (element.getType().equals("roster")) {
                Roster roster = rosterMap.get(element.getId());
                element.getRelationships().getParticipants().forEach(participant -> {
                    Participant participantModel = participantMap.get(participant.getId());
                    roster.addParticipant(participantModel);
                });
            }
        });

        m.setRosters(rosterMap.values());

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
