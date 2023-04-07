package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.config.CacheType;
import com.menu.pubganalyzer.domain.SearchPlayer;
import com.menu.pubganalyzer.domain.dto.SearchPlayerReq;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Participant;
import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.domain.repository.MatchRepository;
import com.menu.pubganalyzer.domain.repository.ParticipantRepository;
import com.menu.pubganalyzer.domain.repository.PlayerRepository;
import com.menu.pubganalyzer.event.SaveMatchesEvent;
import com.menu.pubganalyzer.util.pubgAPI.PubgAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchPlayerService {
    private final PubgAPI pubgAPI;
    private final PlayerRepository playerRepository;
    private final ParticipantRepository participantRepository;
    private final MatchRepository matchRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public SearchPlayer searchPlayer(SearchPlayerReq request, Pageable pageable) {
        Shard shard = request.getShard();
        String nickname = request.getNickname();
        log.info("유저 검색 요청 Shard:{}, Nickname:{}", shard.name(), nickname);

        SearchPlayer searchPlayer = new SearchPlayer();

        pubgAPI.setShard(request.getShard());

        Optional<Player> optionalPlayer = playerRepository.findByShardIdAndName(shard, nickname);

        Player player;
        List<Participant> participants;

        if (optionalPlayer.isPresent()) {
            // 이미 등록된 유저라면, DB에서 Participant를 가져온다.
            player = optionalPlayer.get();
            Page<Participant> participantPage = participantRepository.findByPlayerIdOrderByMatch_CreatedAtDesc(player.getId(), pageable);
            participants = participantPage.getContent();
        } else {
            // 새로 조회하는 유저라면, pubgAPI로 플레이어를 조회한다.
            log.info("새로운 유저 조회 Shard:{}, Nickname:{}", shard.name(), nickname);
            player = pubgAPI.player(nickname);
            playerRepository.save(player);
            // 유저 매치 히스토리를 갱신한다.
            participants = renewHistory(player, matches -> eventPublisher.publishEvent(SaveMatchesEvent.of(matches)));
        }

        searchPlayer.setPlayer(player);
        searchPlayer.setParticipants(participants);

        log.info("유저 검색 완료 Shard:{}, Nickname:{}", shard.name(), nickname);

        return searchPlayer;
    }

    @Cacheable(value = "renew_players", key = "#nickname")
    public boolean renewHistory(String nickname) {
        Player player = playerRepository.findByName(nickname)
                .orElseThrow(() -> new RuntimeException("등록된 유저가 없습니다."));

        pubgAPI.setShard(player.getShardId());
        player = pubgAPI.player(nickname);

        renewHistory(player, matchRepository::saveAll);

        return true;
    }

    private List<Participant> renewHistory(Player player, MatchInsertStrategy matchInsertStrategy) {
        Shard shard = player.getShardId();
        String nickname = player.getName();
        pubgAPI.setShard(shard);
        log.info("전적 갱신 Shard:{}, Nickname:{}", shard.name(), nickname);

        Set<String> matchIds = player.getMatchIds();

        // DB에서 검색
        Set<Match> matches = findMatch(matchIds);

        // 결과값 id로 매핑
        Set<String> existsMatchIds = Match.extractIds(matches);

        // DB에 없는 데이터 취합 : 입력값과 결과값의 차집합
        matchIds.removeAll(existsMatchIds);

        // DB에 없는 데이터 api로 조회
        Set<Match> response = getMatch(matchIds);

        matchInsertStrategy.insert(response);

        // redis add

        matches.addAll(response);

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

    private Set<Match> findMatch(Collection<String> matchIds) {
        // 추후 캐시 검색도 추가.
        return matchRepository.findByIdInFetchParticipant(matchIds);
    }

    private Set<Match> getMatch(Collection<String> matchIds) {
        return matchIds.stream()
                .parallel()
                .map(pubgAPI::match)
                .collect(Collectors.toSet());
    }

    private interface MatchInsertStrategy {
        void insert(Collection<Match> matches);
    }
}
