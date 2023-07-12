package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class PlayerRes {
    private String id;
    private String nickname;
    private Shard shard;
    private Set<String> matchIds;

    private PlayerRes() {
    }

    public static PlayerRes of(Player player) {
        return PlayerRes.builder()
                .id(player.getId())
                .nickname(player.getName())
                .shard(Shard.valueOf(player.getShardId()))
                .matchIds(player.getMatchIds())
                .build();
    }
}
