package com.menu.pubganalyzer.util.pubgAPI;

import com.menu.pubganalyzer.model.Match;
import com.menu.pubganalyzer.model.Player;
import com.menu.pubganalyzer.model.enums.Shard;

import java.util.Collection;
import java.util.Set;

public interface PubgAPI {
    void setShard(Shard shard);

    Match match(String matchId);

    Player player(String nickname);

    Set<Player> player(Collection<String> nicknames);
}
