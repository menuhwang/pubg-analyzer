package com.menu.pubganalyzer.domain.model;

import com.menu.pubganalyzer.util.pubgAPI.response.PlayersResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;

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
    `name`          VARCHAR(255) NOT NULL,
    `title_id`      VARCHAR(255) NOT NULL,
    `shard_id`      VARCHAR(255) NOT NULL,
    `patch_version` VARCHAR(255) NULL,
    `ban_type`      VARCHAR(20) NOT NULL,
    `clan_id`       VARCHAR(40) NULL,
    `created_datetime` datetime    NOT NULL,
    `updated_datetime` datetime    NULL,
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
    private String shardId;
    private String patchVersion;
    private String banType;
    private String clanId;
    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<PlayerMatch> playerMatches = new HashSet<>();

    @CreatedDate
    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDatetime;

    protected Player() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShardId() {
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

    public void updateShard(String shard) {
        if (shard == null) return;
        this.shardId = shard;
    }

    public void addMatch(String matchId) {
        PlayerMatch playerMatch = PlayerMatch.of(this, matchId);
        this.playerMatches.add(playerMatch);
    }

    public void updateMatchHistory() {
        this.updatedDatetime = LocalDateTime.now();
    }

    public static List<Player> of(PlayersResponse playersResponse) {
        List<Player> result = new ArrayList<>();
        for (PlayersResponse.Player prp : playersResponse.getPlayers()) {
            Player player = Player.from(prp);
            for (String matchId : prp.getMatchIds()) {
                player.addMatch(matchId);
            }
            result.add(player);
        }
        return result;
    }

    private static Player from(PlayersResponse.Player prp) {
        return Player.builder()
                .id(prp.getId())
                .name(prp.getAttributes().getName())
                .titleId(prp.getAttributes().getTitleId().toUpperCase())
                .shardId(prp.getAttributes().getShardId().toUpperCase())
                .banType(prp.getAttributes().getBanType().toUpperCase())
                .clanId(prp.getAttributes().getClanId())
                .patchVersion(prp.getAttributes().getPatchVersion())
                .build();
    }
}
