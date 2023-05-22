package com.menu.pubganalyzer.facade;

import com.menu.pubganalyzer.domain.SearchPlayer;
import com.menu.pubganalyzer.domain.model.Participant;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlayerFacade {
    SearchPlayer findByNickname(Shard shard, String nickname, Pageable pageable);
    List<Participant> renewAsync(Shard shard, String nickname);
    List<Participant> renewSync(Shard shard, String nickname);
}
