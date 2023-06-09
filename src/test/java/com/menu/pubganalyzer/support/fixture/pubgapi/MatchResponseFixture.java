package com.menu.pubganalyzer.support.fixture.pubgapi;

import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.domain.model.enums.match.GameMode;
import com.menu.pubganalyzer.domain.model.enums.match.MapName;
import com.menu.pubganalyzer.domain.model.enums.match.MatchType;
import com.menu.pubganalyzer.domain.model.enums.match.SeasonState;
import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;

import java.time.LocalDateTime;
import java.util.List;

public class MatchResponseFixture {
    public static final String MATCH_ID = "test-match";
    public static final String MATCH_SHARD = Shard.STEAM.name();

    private static final MatchResponse.Attribute RES_ATTRIBUTE = MatchResponse.Attribute.builder()
            .gameMode(GameMode.DUO.getTag())
            .seasonState(SeasonState.PROGRESS.name())
            .duration(1000)
            .titleId("")
            .shardId(MATCH_SHARD)
            .mapName(MapName.Baltic_Main.name())
            .isCustomMatch(false)
            .matchType(MatchType.OFFICIAL.name())
            .createdAt(LocalDateTime.now())
            .build();

    private static final MatchResponse.Included RES_ROSTERS = MatchResponse.Included.builder()
            .data(List.of())
            .build();

    private static final MatchResponse.Included RES_ASSET = MatchResponse.Included.builder()
            .data(List.of())
            .build();

    private static final MatchResponse.Included RES_PARTICIPANTS = MatchResponse.Included.builder()
            .data(List.of())
            .build();

    private static final MatchResponse.Included RES_TEAM = MatchResponse.Included.builder()
            .data(List.of())
            .build();

    private static final MatchResponse.Relationship RES_RELATIONSHIP = MatchResponse.Relationship.builder()
            .rosters(RES_ROSTERS)
            .assets(RES_ASSET)
            .participants(RES_PARTICIPANTS)
            .team(RES_TEAM)
            .build();

    private static final MatchResponse.Data RES_DATA = MatchResponse.Data.builder()
            .id(MATCH_ID)
            .attributes(RES_ATTRIBUTE)
            .relationships(RES_RELATIONSHIP)
            .build();

    public static final MatchResponse MATCH_RESPONSE = MatchResponse.builder()
            .data(RES_DATA)
            .included(List.of())
            .build();
}
