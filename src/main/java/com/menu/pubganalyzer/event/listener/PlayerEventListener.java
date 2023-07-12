package com.menu.pubganalyzer.event.listener;

import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.domain.model.PlayerMatch;
import com.menu.pubganalyzer.domain.repository.PlayerMatchRepository;
import com.menu.pubganalyzer.domain.repository.PlayerRepository;
import com.menu.pubganalyzer.event.UpdateMatchHistoryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PlayerEventListener {
    private final PlayerRepository playerRepository;
    private final PlayerMatchRepository playerMatchRepository;

    @EventListener
    @Transactional
    public void updateMatchHistory(UpdateMatchHistoryEvent event) {
        Player player = event.getPlayer();
        Set<PlayerMatch> exists = playerMatchRepository.findByPlayer(player);
        Set<PlayerMatch> playerMatches = new HashSet<>(event.getPlayerMatches());
        playerMatches.removeAll(exists);

        playerMatchRepository.saveAll(playerMatches);

        player.updateMatchHistory();
        playerRepository.save(player);
    }
}
