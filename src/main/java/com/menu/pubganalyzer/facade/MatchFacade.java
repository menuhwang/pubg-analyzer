package com.menu.pubganalyzer.facade;

import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.enums.Shard;

public interface MatchFacade {
    Match findById(Shard shard, String id);
}
