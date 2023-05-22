package com.menu.pubganalyzer.facade;

import com.menu.pubganalyzer.domain.model.Match;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public interface MatchFacade {
    Match findById(String id);
    default Set<Match> findById(Collection<String> ids) {
        return ids.stream()
                .parallel()
                .map(this::findById)
                .collect(Collectors.toSet());
    }
}
