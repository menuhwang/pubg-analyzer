package com.menu.pubganalyzer.domain.dto;

import java.util.List;

public class MatchDeleteRequest {
    private List<String> matches;

    public List<String> getMatches() {
        return matches;
    }

    public void setMatches(List<String> matches) {
        this.matches = matches;
    }
}
