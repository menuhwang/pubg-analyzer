package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.SearchPlayer;
import com.menu.pubganalyzer.domain.dto.SearchPlayerReq;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Participant;
import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.domain.repository.MatchRepository;
import com.menu.pubganalyzer.domain.repository.ParticipantRepository;
import com.menu.pubganalyzer.domain.repository.PlayerRepository;
import com.menu.pubganalyzer.util.pubgAPI.PubgAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchPlayerService {
    private final PubgAPI pubgAPI;
    private final PlayerRepository playerRepository;
    private final ParticipantRepository participantRepository;
    private final MatchRepository matchRepository;

    @Transactional
    public SearchPlayer searchPlayer(SearchPlayerReq request, Pageable pageable) {
        Shard shard = request.getShard();
        String nickname = request.getNickname();

        SearchPlayer searchPlayer = new SearchPlayer();

        pubgAPI.setShard(request.getShard());

        Player player = playerRepository.findByShardIdAndName(shard, nickname)
                .orElseGet(() -> {
                    Player p = pubgAPI.player(nickname);
                    playerRepository.save(p);
                    return p;
                });

        searchPlayer.setPlayer(player);

        if (player.getMatchIds().isEmpty()) {
            Page<Participant> participantPage = participantRepository.findByPlayerIdOrderByMatch_CreatedAtDesc(player.getId(), pageable);
            searchPlayer.setParticipants(participantPage.getContent());
            return searchPlayer;
        }

        Set<String> matchIds = player.getMatchIds();

        // DB에서 검색
        Set<Match> matches = matchRepository.findByIdInFetchParticipant(matchIds);

        // 결과값 id로 매핑
        Set<String> exsistsMatchIds = matches.stream()
                .map(Match::getId)
                .collect(Collectors.toSet());

        // DB에 없는 데이터 취합 : 입력값과 결과값의 차집합
        matchIds.removeAll(exsistsMatchIds);

        // DB에 없는 데이터 api로 조회
        Set<Match> response = matchIds.stream()
                .parallel()
                .map(pubgAPI::match)
                .collect(Collectors.toSet());

        matchRepository.saveAll(response); // batch
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

        searchPlayer.setParticipants(participants);

        return searchPlayer;
    }
}
