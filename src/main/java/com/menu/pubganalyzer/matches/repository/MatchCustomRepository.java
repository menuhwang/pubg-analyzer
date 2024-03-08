package com.menu.pubganalyzer.matches.repository;

import com.menu.pubganalyzer.matches.dto.request.MatchFindCondition;
import com.menu.pubganalyzer.matches.model.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MatchCustomRepository {
    Page<Match> findByPlayerName(String playerName, Pageable pageable, MatchFindCondition matchFindCondition);
}
