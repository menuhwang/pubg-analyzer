package com.menu.pubganalyzer.event.publisher;

import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.event.SaveMatchesEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public void saveMatches(List<Match> matches) {
        eventPublisher.publishEvent(SaveMatchesEvent.of(matches));
    }
}
