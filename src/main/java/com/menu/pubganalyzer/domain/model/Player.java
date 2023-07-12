package com.menu.pubganalyzer.domain.model;

import com.menu.pubganalyzer.util.pubgAPI.response.PlayersResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    `created_datetime` datetime    NULL,
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
@EntityListeners(AuditingEntityListener.class)
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
    @Transient
    private List<String> matches;

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

    public List<String> getMatches() {
        return matches;
    }

    public void updateShard(String shard) {
        if (shard == null) return;
        this.shardId = shard;
    }

    public void updateMatchHistory() {
        this.updatedDatetime = LocalDateTime.now();
    }

    public static List<Player> of(PlayersResponse playersResponse) {
        List<Player> result = new ArrayList<>();
        for (PlayersResponse.Player prp : playersResponse.getPlayers()) {
            Player player = Player.from(prp);
            result.add(player);
        }
        return result;
    }

    private static Player from(PlayersResponse.Player prp) {
        return Player.builder()
                .id(prp.getId())
                .name(prp.getAttributes().getName())
                .titleId(prp.getAttributes().getTitleId())
                .shardId(prp.getAttributes().getShardId())
                .banType(prp.getAttributes().getBanType())
                .clanId(prp.getAttributes().getClanId())
                .patchVersion(prp.getAttributes().getPatchVersion())
                .matches(prp.getMatchIds())
                .build();
    }
}
