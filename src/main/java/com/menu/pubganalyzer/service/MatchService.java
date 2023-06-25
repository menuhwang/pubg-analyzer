package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.dao.MatchDAO;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.repository.PlayerMatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final PlayerMatchRepository playerMatchRepository;
    private final MatchDAO matchDAO;

    public Page<Match> findById(String id, Pageable pageable) {
        return matchDAO.findById(id, pageable);
    }

    public Page<Match> findAll(Pageable pageable) {
        return matchDAO.findAll(pageable);
    }

    @Transactional
    public void deleteById(Collection<String> ids) {
        for (String id : ids) {
            playerMatchRepository.deleteByMatchId(id);
            matchDAO.deleteById(id);
        }
    }
}
