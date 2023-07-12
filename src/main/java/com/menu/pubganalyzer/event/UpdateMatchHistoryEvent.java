package com.menu.pubganalyzer.event;

import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.domain.model.PlayerMatch;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UpdateMatchHistoryEvent extends Event {
    private final Player player;
    private final Set<PlayerMatch> playerMatches;

    private UpdateMatchHistoryEvent(Player player, Collection<PlayerMatch> playerMatches) {
        this.player = player;
        this.playerMatches = new HashSet<>(playerMatches);
    }

    public static UpdateMatchHistoryEvent of(Player player, List<PlayerMatch> playerMatches) {
        return new UpdateMatchHistoryEvent(player, playerMatches);
    }

    public Player getPlayer() {
        return player;
    }

    public Set<PlayerMatch> getPlayerMatches() {
        return playerMatches;
    }
}
