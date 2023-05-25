package com.menu.pubganalyzer.facade;

import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.domain.repository.MatchRepository;
import com.menu.pubganalyzer.util.pubgAPI.PubgAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MatchFacadeImpl implements MatchFacade {
    private final PubgAPI pubgAPI;
    private final MatchRepository matchRepository;

    /**
     * 캐시에서 검색, DB에서 검색 후 없는 매치를 api에서 검색해온다.
     */
    @Override
    @Cacheable(value = "matches", key = "#shard + '_' + #id")
    public Match findById(Shard shard, String id) {
        return matchRepository.findByIdFetchParticipant(id)
                .orElse(Match.of(pubgAPI.match(shard.name(), id)));
    }
}
