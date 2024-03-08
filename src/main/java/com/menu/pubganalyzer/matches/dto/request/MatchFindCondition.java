package com.menu.pubganalyzer.matches.dto.request;

import java.util.List;
import java.util.Objects;

public record MatchFindCondition(
        List<String> matchTypes
) {
    public MatchFindCondition(List<String> matchTypes) {
        if (Objects.nonNull(matchTypes))
            this.matchTypes = matchTypes.stream().map(String::toUpperCase).toList();
        else
            this.matchTypes = null;
    }

    public boolean hasMatchTypeCondition() {
        return Objects.nonNull(matchTypes) && !matchTypes.isEmpty();
    }
}
