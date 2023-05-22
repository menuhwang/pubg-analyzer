package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.SearchPlayer;
import com.menu.pubganalyzer.domain.dto.SearchPlayerReq;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.facade.PlayerFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchPlayerService {
    private final PlayerFacade playerFacade;

    @Transactional
    public SearchPlayer searchPlayer(SearchPlayerReq request, Pageable pageable) {
        Shard shard = request.getShard();
        String nickname = request.getNickname();

        return playerFacade.findByNickname(shard, nickname, pageable);
    }

    @Cacheable(value = "renew_players", key = "#nickname")
    public boolean renew(Shard shard, String nickname) {
        playerFacade.renewSync(shard, nickname);
        return true;
    }
}
