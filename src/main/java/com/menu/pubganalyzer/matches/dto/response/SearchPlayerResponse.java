package com.menu.pubganalyzer.matches.dto.response;

import com.menu.pubganalyzer.common.dto.response.PageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SearchPlayerResponse {
    private final String player;
    private final PageDTO<MatchStatsResponse> matches;

    public static SearchPlayerResponse of(String player, PageDTO<MatchStatsResponse> matches) {
        return SearchPlayerResponse.builder()
                .player(player)
                .matches(matches)
                .build();
    }
}
