package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.model.matches.Participant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ParticipantRes {
    private final String name;
    private final int rank;
    private final int kills;
    private final int assists;
    private final float damageDealt;
    private final int revives;

    public static ParticipantRes from(Participant participant) {
        return ParticipantRes.builder()
                .name(participant.getName())
                .rank(participant.getWinPlace())
                .kills(participant.getKills())
                .assists(participant.getAssists())
                .damageDealt(participant.getDamageDealt())
                .revives(participant.getRevives())
                .build();
    }
}
