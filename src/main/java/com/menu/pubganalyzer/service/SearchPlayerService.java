package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.SearchPlayer;
import com.menu.pubganalyzer.domain.dao.MatchDAO;
import com.menu.pubganalyzer.domain.dao.ParticipantDAO;
import com.menu.pubganalyzer.domain.dao.PlayerDAO;
import com.menu.pubganalyzer.domain.dto.SearchPlayerReq;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Participant;
import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.domain.model.PlayerMatch;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.domain.repository.PlayerMatchRepository;
import com.menu.pubganalyzer.event.publisher.MatchEventPublisher;
import com.menu.pubganalyzer.event.publisher.PlayerEventPublisher;
import com.menu.pubganalyzer.exception.MatchNotFoundException;
import com.menu.pubganalyzer.exception.PlayerNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchPlayerService {
    private final PlayerDAO playerDAO;
    private final ParticipantDAO participantDAO;
    private final MatchDAO matchDAO;
    private final PlayerMatchRepository playerMatchRepository;
    private final PlayerEventPublisher playerEventPublisher;
    private final MatchEventPublisher matchEventPublisher;

    @Transactional
    public SearchPlayer searchPlayer(SearchPlayerReq request, Pageable pageable) {
        Shard shard = request.getShard();
        String nickname = request.getNickname();

        Player player;
        try {
            player = playerDAO.findByNickname(nickname);
        } catch (PlayerNotFoundException e) {
            return SearchPlayer.of(Player.temp(shard, nickname), Page.empty());
        }

        Page<PlayerMatch> playerMatches = playerMatchRepository.findByPlayer(player, pageable);
        final String playerId = player.getId();

        List<String> matchIds = playerMatches.stream()
                .map(PlayerMatch::getMatchId)
                .collect(Collectors.toList());

        List<Participant> participants = matchIds.stream()
                .map(matchId -> participantDAO.findByMatchIdAndPlayerId(matchId, playerId))
                .collect(Collectors.toList());

        return SearchPlayer.of(player, new PageImpl<>(participants, playerMatches.getPageable(), playerMatches.getTotalElements()));
    }

    public void updateMatchHistory(String nickname) {
        Player player = playerDAO.fetch(nickname);
        List<Match> matches = player.getMatchIds().stream()
                .parallel()
                .map(id -> {
                    try {
                        return matchDAO.findById(id);
                    } catch (MatchNotFoundException e) {
                        log.warn("[{}] {} match id:{}", e.getClass().getName(), e.getMessage(), e.getMatchId());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        for (Match match : matches) player.addMatch(match.getId(), match.getCreatedAt());

        if (matches.get(0).getShardId() != player.getShardId()) {
            player.updateShard(matches.get(0).getShardId());
            playerDAO.save(player);
        }

        playerEventPublisher.updateMatchHistory(player);
        matchEventPublisher.saveMatches(matches);
    }
}
