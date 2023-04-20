package com.menu.pubganalyzer.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.util.pubgAPI.response.PlayersResponse;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Entity
@Table(indexes = {
        @Index(name = "player_name_shard_index", columnList = "name, shardId", unique = true),
})
public class Player {
    @Id
    private String id;
    private String name;
    private String titleId;
    @Enumerated(EnumType.STRING)
    private Shard shardId;
    private String patchVersion;
    @Transient
    @JsonIgnore
    Set<String> matchIds = new HashSet<>();

    protected Player() {
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

    public static List<Player> of(PlayersResponse playersResponse) {
        return playersResponse.getPlayers().stream()
                .map(player -> Player.builder()
                        .id(player.getId())
                        .name(player.getAttributes().getName())
                        .titleId(player.getAttributes().getTitleId())
                        .shardId(Shard.valueOf((player.getAttributes().getShardId()).toUpperCase()))
                        .patchVersion(player.getAttributes().getPatchVersion())
                        .matchIds(player.getRelationships().getMatches().stream().map(PlayersResponse.Element::getId).collect(Collectors.toSet()))
                        .build()
                ).collect(Collectors.toList());
    }
}
