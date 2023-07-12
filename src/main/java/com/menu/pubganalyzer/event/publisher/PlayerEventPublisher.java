package com.menu.pubganalyzer.event.publisher;

import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.domain.model.PlayerMatch;
import com.menu.pubganalyzer.event.UpdateMatchHistoryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlayerEventPublisher {
    private final ApplicationEventPublisher publisher;

    public void updateMatchHistory(Player player, List<PlayerMatch> playerMatches) {
        publisher.publishEvent(UpdateMatchHistoryEvent.of(player, playerMatches));
    }
}
