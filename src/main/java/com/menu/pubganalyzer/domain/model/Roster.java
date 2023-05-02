package com.menu.pubganalyzer.domain.model;

import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Entity
public class Roster {
    @Id
    private String id;
    private boolean won;
    @Enumerated(EnumType.STRING)
    private Shard shardId;
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
    private Roster(String id, boolean won, Shard shardId, int rank, int teamId, Match match) {
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
                .shardId(Shard.valueOf((roster.getAttributes().getShardId()).toUpperCase()))
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
