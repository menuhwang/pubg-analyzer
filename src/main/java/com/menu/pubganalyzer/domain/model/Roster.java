package com.menu.pubganalyzer.domain.model;

import com.menu.pubganalyzer.domain.model.enums.Shard;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.*;
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roster")
    private Set<Participant> participants = new HashSet<>();
    @Transient
    private Set<String> participantIds;

    protected Roster() {
    }

    @Builder
    private Roster(String id, boolean won, Shard shardId, int rank, int teamId, Collection<String> participantIds, Match match) {
        this.id = id;
        this.won = won;
        this.shardId = shardId;
        this.rank = rank;
        this.teamId = teamId;
        this.participantIds = new HashSet<>(participantIds);
        this.match = match;
    }

    public static Roster of(Map<String, Object> raw, Match match) {
        String type = (String) raw.getOrDefault("type", null);
        if (type == null || !type.equals("roster")) throw new IllegalArgumentException("정상적인 로스터 데이터를 입력해주세요.");

        Map<String, Object> attributes = (Map<String, Object>) raw.get("attributes");
        Map<String, Object> stats = (Map<String, Object>) attributes.get("stats");
        List<String> participants = ((Map<String, List<Map<String, String>>>) (((Map<String, Object>) raw.get("relationships")).get("participants"))).get("data").stream().map(m -> m.get("id")).collect(Collectors.toList());

        return Roster.builder()
                .id((String) raw.get("id"))
                .won(Boolean.parseBoolean((String) attributes.get("won")))
                .shardId(Shard.valueOf(((String) attributes.get("shardId")).toUpperCase()))
                .rank((int) stats.get("rank"))
                .teamId((int) stats.get("teamId"))
                .participantIds(participants)
                .match(match)
                .build();
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
