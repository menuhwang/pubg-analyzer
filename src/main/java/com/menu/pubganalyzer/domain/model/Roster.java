package com.menu.pubganalyzer.domain.model;

import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
CREATE TABLE roster
        (
        `id`        CHAR(36)  NOT NULL,
        `won`       BIT(1)       NOT NULL,
        `shard_id`  VARCHAR(255) NULL,
        `win_place` INT          NULL,
        `team_id`   INT          NOT NULL,
        `match_id`  CHAR(36)  NULL,
        CONSTRAINT pk_roster PRIMARY KEY (id)
        );

        ALTER TABLE roster
        ADD CONSTRAINT FK_ROSTER_ON_MATCH FOREIGN KEY (match_id) REFERENCES matches (id);
*/

@Getter
@Entity(name = "roster")
public class Roster {
    @Id
    @Column(length = 36)
    private String id;
    private boolean won;
    private String shardId;
    @Column(name = "winPlace")
    private int rank;
    private int teamId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Match match;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roster", cascade = CascadeType.ALL)
    private Set<Participant> participants = new HashSet<>();

    protected Roster() {
    }

    public void addParticipant(Participant participant) {
        this.participants.add(participant);
        participant.setRoster(this);
        participant.setMatch(this.match);
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    @Builder
    private Roster(String id, boolean won, String shardId, int rank, int teamId, Match match) {
        this.id = id;
        this.won = won;
        this.shardId = shardId;
        this.rank = rank;
        this.teamId = teamId;
        this.match = match;
    }

    public static Roster of(MatchResponse.Element roster) {
        return Roster.builder()
                .id(roster.getId())
                .won(roster.getAttributes().getWon())
                .shardId(roster.getAttributes().getShardId())
                .rank(roster.getAttributes().getStats().getRank())
                .teamId(roster.getAttributes().getStats().getTeamId())
                .build();
    }

    public Set<String> extractParticipantName() {
        return this.participants.stream()
                .map(Participant::getName)
                .collect(Collectors.toSet());
    }

    public Set<String> extractParticipantNameWithout(String nickname) {
        Set<String> participantNames = extractParticipantName();
        participantNames.remove(nickname);
        return participantNames;
    }

    @Override
    public String toString() {
        return "Roster{" +
                "id='" + id + '\'' +
                ", won=" + won +
                ", shardId=" + shardId +
                ", rank=" + rank +
                ", teamId=" + teamId +
                ", match=" + match.getId() +
                '}';
    }
}
