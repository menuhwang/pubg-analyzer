package com.menu.pubganalyzer.domain.dao;

import com.menu.pubganalyzer.domain.model.Player;

public interface PlayerDAO {
    /**
     * 캐싱된 데이터를 가져오는 것은 괜찮으나, 결과를 캐싱해선 안됨.
     * @param nickname 플레이어 PUBG 닉네임
     * @return 캐싱된 데이터가 있다면, 캐시를 반환하고 없다면 DB에서 조회한 결과를 반환한다. 단, 결과를 캐싱하진 않는다.
     */
    Player findByNickname(String nickname);

    /**
     * PUBG API에 플레이어 정보를 요청한다.
     * 요청한 정보를 캐싱하고 저장된 player가 아니면 DB에 저장한다.
     * @param nickname 플레이어 PUBG 닉네임
     * @return PUBG API에 플레이어 정보를 요청 후 캐싱하고 결과를 반환한다.
     */
    Player fetch(String nickname);
    void save(Player player);
}
