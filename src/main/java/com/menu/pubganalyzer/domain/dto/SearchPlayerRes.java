package com.menu.pubganalyzer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SearchPlayerRes {
    private final String player;
    private final PageDTO<MatchStatsRes> matches;

    public static SearchPlayerRes of(String player, PageDTO<MatchStatsRes> matches) {
        return SearchPlayerRes.builder()
                .player(player)
                .matches(matches)
                .build();
    }
}
