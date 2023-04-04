package com.menu.pubganalyzer.model.dto;

import com.menu.pubganalyzer.model.Player;
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
    private String shard;
    private Set<String> matchIds;

    private PlayerRes() {
    }

    public static PlayerRes of(Player player) {
        return PlayerRes.builder()
                .id(player.getId())
                .nickname(player.getName())
                .shard(player.getShardId().name().toLowerCase())
                .matchIds(player.getMatchIds())
                .build();
    }
}
