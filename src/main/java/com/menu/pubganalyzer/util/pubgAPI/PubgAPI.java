package com.menu.pubganalyzer.util.pubgAPI;

import com.menu.pubganalyzer.model.Match;
import com.menu.pubganalyzer.model.enums.Shard;

public interface PubgAPI {
    void setShard(Shard shard);
    Match match(String matchId);
}
