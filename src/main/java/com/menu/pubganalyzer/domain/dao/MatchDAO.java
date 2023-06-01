package com.menu.pubganalyzer.domain.dao;

import com.menu.pubganalyzer.domain.model.Match;

public interface MatchDAO {
    Match findById(String id);
}
