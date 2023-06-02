package com.menu.pubganalyzer.event.publisher;

import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.event.UpdateMatchHistoryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerEventPublisher {
    private final ApplicationEventPublisher publisher;

    public void updateMatchHistory(Player player) {
        publisher.publishEvent(UpdateMatchHistoryEvent.of(player));
    }
}
