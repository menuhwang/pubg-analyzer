package com.menu.pubganalyzer.event.listener;

import com.menu.pubganalyzer.domain.repository.PlayerMatchRepository;
import com.menu.pubganalyzer.event.UpdateMatchHistoryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerEventListener {
    private final PlayerMatchRepository playerMatchRepository;

    @EventListener
    public void updateMatchHistory(UpdateMatchHistoryEvent event) {
        playerMatchRepository.saveAll(event.getPlayerMatches());
    }
}