package com.menu.pubganalyzer.support.fixture;

import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.support.fixture.pubgapi.PlayerResponseFixture;

public class PlayerFixture {
    public static final String PLAYER_SHARD = PlayerResponseFixture.PLAYER_SHARD;
    public static final String PLAYER_NAME = PlayerResponseFixture.PLAYER_NAME;

    public static Player PLAYER = Player.of(PlayerResponseFixture.PLAYERS_RESPONSE).get(0);
}
