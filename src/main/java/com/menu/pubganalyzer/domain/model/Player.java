package com.menu.pubganalyzer.domain.model;

import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.util.pubgAPI.response.PlayersResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
CREATE TABLE player
        (
        `id`            CHAR(40)  NOT NULL,
        `name`          VARCHAR(255) NULL,
        `title_id`      VARCHAR(255) NULL,
        `shard_id`      VARCHAR(255) NULL,
        `patch_version` VARCHAR(255) NULL,
        CONSTRAINT pk_player PRIMARY KEY (id)
        );

        CREATE UNIQUE INDEX player_name_shard_index ON player (name, shard_id);
*/

@Entity(name = "player")
@Table(indexes = {
        @Index(name = "player_name_shard_index", columnList = "name, shardId", unique = true),
})
@Builder
@AllArgsConstructor
public class Player {
    @Id
    @Column(length = 40)
    private String id;
    private String name;
    private String titleId;
    @Enumerated(EnumType.STRING)
    private Shard shardId;
    private String patchVersion;
    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<PlayerMatch> playerMatches = new HashSet<>();

    protected Player() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Shard getShardId() {
        return shardId;
    }

    public Set<PlayerMatch> getPlayerMatches() {
        return playerMatches;
    }

    public Set<String> getMatchIds() {
        return playerMatches.stream()
                .map(PlayerMatch::getMatchId)
                .collect(Collectors.toSet());
    }

    public void updateShard(Shard shard) {
        if (shard == null) return;
        this.shardId = shard;
    }

    public void addMatch(String matchId, LocalDateTime createdDateTime) {
        PlayerMatch playerMatch = PlayerMatch.of(this, matchId, createdDateTime);
        this.playerMatches.remove(playerMatch);
        this.playerMatches.add(playerMatch);
    }

    public static Player temp(Shard shard, String nickname) {
        return Player.builder()
                .shardId(shard)
                .name(nickname)
                .build();
    }

    public static List<Player> of(PlayersResponse playersResponse) {
        List<Player> result = new ArrayList<>();
        for (PlayersResponse.Player psp : playersResponse.getPlayers()) {
            Player player = Player.builder()
                    .id(psp.getId())
                    .name(psp.getAttributes().getName())
                    .titleId(psp.getAttributes().getTitleId())
                    .shardId(Shard.valueOf((psp.getAttributes().getShardId()).toUpperCase()))
                    .patchVersion(psp.getAttributes().getPatchVersion())
                    .build();
            for (String matchId : psp.getMatchIds()) {
                player.addMatch(matchId, psp.getAttributes().getCreatedAt());
            }
            result.add(player);
        }
        return result;
    }
}
