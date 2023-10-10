package com.menu.pubganalyzer.support.fixture.pubgapi;

import com.menu.pubganalyzer.common.enums.Shard;
import com.menu.pubganalyzer.util.pubgAPI.response.player.*;

import java.util.List;

import static com.menu.pubganalyzer.support.fixture.pubgapi.MatchResponseFixture.MATCH_ID;

public class PlayerResponseFixture {
    public static final String PLAYER_ID = "test-player-id";
    public static final String PLAYER_NAME = "WackyJacky101";
    public static final String PLAYER_SHARD = Shard.STEAM.name();

    private static final Attribute RES_ATTRIBUTE = Attribute.builder()
            .name(PLAYER_NAME)
            .titleId("")
            .shardId(PLAYER_SHARD)
            .patchVersion("")
            .banType("Innocent")
            .clanId("clan.00001111222233334444555566667777")
            .build();

    private static final Included RES_ASSET = Included.builder()
            .data(List.of())
            .build();

    private static final Element ELEMENT_MATCH =  Element.builder()
            .type("match")
            .id(MATCH_ID)
            .build();
    private static final Included RES_MATCHES = Included.builder()
            .data(List.of(ELEMENT_MATCH))
            .build();

    private static final Relationship RES_RELATIONSHIP = Relationship.builder()
            .assets(RES_ASSET)
            .matches(RES_MATCHES)
            .build();

    private static final Player RES_PLAYER = Player.builder()
            .id(PLAYER_ID)
            .attributes(RES_ATTRIBUTE)
            .relationships(RES_RELATIONSHIP)
            .build();

    public static final PlayersResponse PLAYERS_RESPONSE = PlayersResponse.builder()
            .data(List.of(RES_PLAYER))
            .build();
}
