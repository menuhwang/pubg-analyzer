package com.menu.pubganalyzer.facade;

import com.menu.pubganalyzer.domain.SearchPlayer;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Participant;
import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.domain.repository.MatchRepository;
import com.menu.pubganalyzer.domain.repository.ParticipantRepository;
import com.menu.pubganalyzer.domain.repository.PlayerRepository;
import com.menu.pubganalyzer.event.SaveMatchesEvent;
import com.menu.pubganalyzer.exception.PlayerNotFoundException;
import com.menu.pubganalyzer.exception.ServerException;
import com.menu.pubganalyzer.util.pubgAPI.PubgAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlayerFacadeImpl implements PlayerFacade {
    private final MatchRepository matchRepository;
    private final PubgAPI pubgAPI;
    private final MatchFacade matchFacade;
    private final PlayerRepository playerRepository;
    private final ParticipantRepository participantRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public SearchPlayer findByNickname(Shard shard, String nickname, Pageable pageable) {
        log.info("유저 검색 요청 Shard:{}, Nickname:{}", shard.name(), nickname);

        SearchPlayer searchPlayer = new SearchPlayer();

        Player player;
        List<Participant> participants;

        Optional<Player> optionalPlayer = playerRepository.findByShardIdAndName(shard, nickname);

        if (optionalPlayer.isPresent()) {
            // 이미 등록된 유저라면, DB에서 Participant를 가져온다.
            player = optionalPlayer.get();
            Page<Participant> participantPage = participantRepository.findByPlayerIdOrderByRoster_Match_CreatedAtDesc(player.getId(), pageable);
            participants = participantPage.getContent();
        } else {
            // 새로 조회하는 유저라면,
            // 유저 매치 히스토리를 갱신한다.
            log.info("새로운 유저 조회 Shard:{}, Nickname:{}", shard.name(), nickname);
            participants = renewAsync(shard, nickname);
            player = playerRepository.findByName(nickname)
                    .orElseThrow(ServerException::new);
        }

        searchPlayer.setPlayer(player);
        searchPlayer.setParticipants(participants);

        log.info("유저 검색 완료 Shard:{}, Nickname:{}", shard.name(), nickname);

        return searchPlayer;
    }

    @Override
    public List<Participant> renewAsync(Shard shard, String nickname) {
        return renew(shard, nickname, matches -> eventPublisher.publishEvent(SaveMatchesEvent.of(matches)));
    }

    @Override
    public List<Participant> renewSync(Shard shard, String nickname) {
        return renew(shard, nickname, matchRepository::saveAll);
    }

    private List<Participant> renew(Shard shard, String nickname, MatchInsertStrategy matchInsertStrategy) throws PlayerNotFoundException {
        log.info("전적 갱신 Shard:{}, Nickname:{}", shard.name(), nickname);

        pubgAPI.setShard(shard);
        Player player = Player.of(pubgAPI.player(List.of(nickname))).get(0);
        playerRepository.findById(player.getId()).orElseGet(() -> playerRepository.saveAndFlush(player));

        Set<String> matchIds = player.getMatchIds();

        Set<Match> matches = matchIds.stream()
                .parallel()
                .map(matchFacade::findById)
                .collect(Collectors.toSet());

        matchInsertStrategy.insert(matches);

        List<Participant> participants = new ArrayList<>();
        for (Match match : matches) {
            participants.add(match.getParticipant(player));
        }

        participants.sort((p1, p2) -> {
            Match m1 = p1.getMatch();
            Match m2 = p2.getMatch();
            int order = m1.getCreatedAt().compareTo(m2.getCreatedAt());
            return order * -1;
        });

        return participants;
    }

    interface MatchInsertStrategy {
        void insert(Collection<Match> matches);
    }
}
