package com.menu.pubganalyzer.matches.dto.response;

import com.menu.pubganalyzer.matches.model.Participant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
public class ParticipantResponse {
    private final String name;
    private final int rank;
    private final int kills;
    private final int assists;
    private final float damageDealt;
    private final int revives;

    public static ParticipantResponse from(Participant participant) {
        return ParticipantResponse.builder()
                .name(participant.getName())
                .rank(participant.getWinPlace())
                .kills(participant.getKills())
                .assists(participant.getAssists())
                .damageDealt(participant.getDamageDealt())
                .revives(participant.getRevives())
                .build();
    }
}
