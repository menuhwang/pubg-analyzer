package com.menu.pubganalyzer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.menu.pubganalyzer.model.enums.Shard;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Player {
    private String id;
    private String name;
    private String titleId;
    private Shard shardId;
    private String patchVersion;
    @JsonIgnore
    Set<String> matchIds;

    private Player() {
    }

    @Builder
    private Player(String id, String name, String titleId, Shard shardId, String patchVersion, Set<String> matchIds) {
        this.id = id;
        this.name = name;
        this.titleId = titleId;
        this.shardId = shardId;
        this.patchVersion = patchVersion;
        this.matchIds = matchIds;
    }

    public static Player of(Map<String, Object> raw) {
        String type = (String) raw.getOrDefault("type", null);
        if (type == null || !type.equals("player")) throw new IllegalArgumentException("정상적인 유저 데이터를 입력해주세요.");

        Map<String, String> attributes = (Map<String, String>) raw.get("attributes");
        Set<String> matchIds = ((Map<String, List<Map<String, String>>>) ((Map<String, Object>) raw.get("relationships")).get("matches")).get("data").stream().map(m -> m.get("id")).collect(Collectors.toSet());

        return Player.builder()
                .id((String) raw.get("id"))
                .name(attributes.get("name"))
                .titleId(attributes.get("titleId"))
                .shardId(Shard.valueOf(((String) attributes.get("shardId")).toUpperCase()))
                .patchVersion(attributes.get("patchVersion"))
                .matchIds(matchIds)
                .build();
    }
}