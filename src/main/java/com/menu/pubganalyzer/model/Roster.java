package com.menu.pubganalyzer.model;

import com.menu.pubganalyzer.model.enums.Shard;
import lombok.Builder;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Roster {
    private String id;
    private boolean won;
    private Shard shardId;
    private int rank;
    private int teamId;
    private Match match;
    private Set<String> participants; //Entity ignore

    private Roster() {
    }

    @Builder
    private Roster(String id, boolean won, Shard shardId, int rank, int teamId, Collection<String> participants, Match match) {
        this.id = id;
        this.won = won;
        this.shardId = shardId;
        this.rank = rank;
        this.teamId = teamId;
        this.participants = new HashSet<>(participants);
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
                .participants(participants)
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
