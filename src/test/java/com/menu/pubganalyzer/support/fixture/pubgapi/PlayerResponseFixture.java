package com.menu.pubganalyzer.support.fixture.pubgapi;

import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.util.pubgAPI.response.PlayersResponse;

import java.util.List;

public class PlayerResponseFixture {
    public static final String PLAYER_ID = "test-player-id";
    public static final String PLAYER_NAME = "test-player";
    public static final String PLAYER_SHARD = Shard.STEAM.name();

    private static final PlayersResponse.Attribute RES_ATTRIBUTE = PlayersResponse.Attribute.builder()
            .name(PLAYER_NAME)
            .titleId("")
            .shardId(PLAYER_SHARD)
            .patchVersion("")
            .banType("Innocent")
            .clanId("clan.00001111222233334444555566667777")
            .build();

    private static final PlayersResponse.Included RES_ASSET = PlayersResponse.Included.builder()
            .data(List.of())
            .build();
    private static final PlayersResponse.Included RES_MATCHES = PlayersResponse.Included.builder()
            .data(List.of())
            .build();

    private static final PlayersResponse.Relationship RES_RELATIONSHIP = PlayersResponse.Relationship.builder()
            .assets(RES_ASSET)
            .matches(RES_MATCHES)
            .build();

    private static final PlayersResponse.Player RES_PLAYER = PlayersResponse.Player.builder()
            .id(PLAYER_ID)
            .attributes(RES_ATTRIBUTE)
            .relationships(RES_RELATIONSHIP)
            .build();

    public static final PlayersResponse PLAYERS_RESPONSE = PlayersResponse.builder()
            .data(List.of(RES_PLAYER))
            .build();
}
