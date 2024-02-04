package com.menu.pubganalyzer.players.service;

import com.menu.pubganalyzer.players.model.Player;
import com.menu.pubganalyzer.matches.model.Match;
import com.menu.pubganalyzer.matches.repository.MatchRepository;
import com.menu.pubganalyzer.fetch.service.PubgService;
import com.menu.pubganalyzer.players.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PubgService pubgService;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;

    @Transactional
    public void updateMatchHistory(String nickname) {
        Player player = pubgService.fetchPlayer(nickname);

        // 새로운 매치 필터링
        List<String> matchIds = player.getMatches();
        matchIds = matchIds.stream()
                .filter(id -> !matchRepository.existsById(id))
                .collect(Collectors.toList());

        if (matchIds.size() > 100) {
            int to = matchIds.size();
            int from = to - 100;
            matchIds = matchIds.subList(from, to);
        }

        List<Match> matches = pubgService.fetchMatches(matchIds);

        Player updatePlayer = playerRepository.findByName(nickname)
                        .orElse(player);

        updatePlayer.validateEqualShard(matches);
        updatePlayer.plusUpdateCount();

        playerRepository.save(updatePlayer);
        matchRepository.saveAll(matches);
    }
}
