package com.menu.pubganalyzer.event;

import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.domain.model.PlayerMatch;

import java.util.Set;

public class UpdateMatchHistoryEvent {
    private final Player player;

    private UpdateMatchHistoryEvent(Player player) {
        this.player = player;
    }

    public static UpdateMatchHistoryEvent of(Player player) {
        return new UpdateMatchHistoryEvent(player);
    }

    public Set<PlayerMatch> getPlayerMatches() {
        return player.getPlayerMatches();
    }
}
