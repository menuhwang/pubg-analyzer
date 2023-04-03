package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.dao.repository.MatchRepository;
import com.menu.pubganalyzer.model.Match;
import com.menu.pubganalyzer.model.Player;
import com.menu.pubganalyzer.model.dto.MatchRes;
import com.menu.pubganalyzer.model.enums.Shard;
import com.menu.pubganalyzer.util.pubgAPI.PubgAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchesService {
    private final PubgAPI pubgAPI;
    private final MatchRepository matchRepository;

    public List<MatchRes> getMatch(Player player) {
        if (!player.getMatchIds().isEmpty()) return getMatch(player.getShardId(), player.getMatchIds());
        Set<Match> matches = matchRepository.findByShardIdAndPlayerId(player.getShardId(), player.getId());
        return matches.stream()
                .map(MatchRes::of)
                .sorted(Comparator.comparing(MatchRes::getCreatedAt))
                .collect(Collectors.toList());
    }

    public List<MatchRes> getMatch(Shard shard, Collection<String> matchIds) {
        Set<String> matchIdsSet = new HashSet<>(matchIds);

        // DB에서 검색
        Set<Match> matches = matchRepository.findByShardIdAndIdIn(shard, matchIds);

        // 결과값 id로 매핑
        Set<String> resultIds = matches.stream()
                .map(Match::getId)
                .collect(Collectors.toSet());

        // DB에 없는 데이터 취합 : 입력값과 결과값의 차집합
        matchIdsSet.removeAll(resultIds);

        // DB에 없는 데이터 api로 조회
        Set<Match> response = matchIdsSet.stream()
                .parallel()
                .map(pubgAPI::match)
                .collect(Collectors.toSet());

        matchRepository.saveAll(response);

        matches.addAll(response);

        return MatchRes.of(matches);
    }

    public Match getMatch(Shard shard, String matchId) {
        pubgAPI.setShard(shard);
        return pubgAPI.match(matchId);
    }
}
