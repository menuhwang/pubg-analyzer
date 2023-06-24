package com.menu.pubganalyzer.domain.dao;

import com.menu.pubganalyzer.domain.model.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MatchDAO {
    Match findById(String id);
    Page<Match> findById(String id, Pageable pageable);
    Page<Match> findAll(Pageable pageable);
    void deleteById(String id);
}
