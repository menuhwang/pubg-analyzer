package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;

    public Page<Match> findById(String id, Pageable pageable) {
        return matchRepository.findByIdContains(id, pageable);
    }

    @Transactional
    public void deleteById(Collection<String> ids) {
        matchRepository.deleteByIdIn(ids);
    }
}
