package com.menu.pubganalyzer.matches.model;

import com.menu.pubganalyzer.common.exception.IllegalPlayerNameException;
import com.menu.pubganalyzer.util.pubgAPI.response.match.Element;
import com.menu.pubganalyzer.util.pubgAPI.response.match.MatchResponse;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Roster {
    private String id;
    private boolean won;
    private String shardId;
    @Column(name = "winPlace")
    private int rank;
    private int teamId;
    private List<Participant> participants = new ArrayList<>();

    protected Roster() {
    }

    public void addParticipant(Participant participant) {
        this.participants.add(participant);
    }

    @Builder
    private Roster(String id, boolean won, String shardId, int rank, int teamId) {
        this.id = id;
        this.won = won;
        this.shardId = shardId;
        this.rank = rank;
        this.teamId = teamId;
    }

    public static Roster of(Element roster) {
        return Roster.builder()
                .id(roster.getId())
                .won(roster.getAttributes().getWon())
                .shardId(roster.getAttributes().getShardId())
                .rank(roster.getAttributes().getStats().getRank())
                .teamId(roster.getAttributes().getStats().getTeamId())
                .build();
    }

    public boolean contains(String playerName) {
        return findParticipantByName(playerName).isPresent();
    }

    private Optional<Participant> findParticipantByName(String playerName) {
        return participants.stream()
                .filter(participant -> participant.getName().equals(playerName))
                .findFirst();
    }

    public Participant getParticipantByName(String playerName) {
        return findParticipantByName(playerName)
                .orElseThrow(IllegalPlayerNameException::new);
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
                '}';
    }
}
