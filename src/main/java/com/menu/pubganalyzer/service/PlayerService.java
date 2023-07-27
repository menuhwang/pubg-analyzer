package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.domain.model.matches.Match;
import com.menu.pubganalyzer.domain.repository.MatchRepository;
import com.menu.pubganalyzer.domain.repository.PlayerRepository;
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

        List<Match> matches = pubgService.fetchMatches(matchIds);

        player.validateEqualShard(matches);
        player.plusUpdateCount();

        playerRepository.save(player);
        matchRepository.saveAll(matches);
    }
}
