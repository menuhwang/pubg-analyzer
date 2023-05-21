package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.enums.DeleteCondition;
import com.menu.pubganalyzer.domain.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Transactional
    public void deleteByCreatedAt(DeleteCondition condition, LocalDateTime createdAt) {
        switch (condition) {
            case EQ:
                matchRepository.deleteByCreatedAtGreaterThanEqualAndCreatedAtLessThan(createdAt, createdAt.plusDays(1L));
                break;
            case LT:
                matchRepository.deleteByCreatedAtLessThan(createdAt);
                break;
            case LE:
                matchRepository.deleteByCreatedAtLessThan(createdAt.plusDays(1L));
                break;
            case GT:
                matchRepository.deleteByCreatedAtGreaterThanEqual(createdAt.plusDays(1L));
                break;
            case GE:
                matchRepository.deleteByCreatedAtGreaterThanEqual(createdAt);
                break;
            default:
                break;
        }
    }
}
