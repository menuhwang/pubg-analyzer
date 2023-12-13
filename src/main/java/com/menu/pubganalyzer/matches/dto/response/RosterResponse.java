package com.menu.pubganalyzer.matches.dto.response;

import com.menu.pubganalyzer.matches.model.Roster;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@Builder
@AllArgsConstructor
public class RosterResponse {
    private final boolean won;
    private final List<ParticipantResponse> participants;

    public static RosterResponse from(Roster roster) {
        List<ParticipantResponse> participantRes = roster.getParticipants().stream().map(ParticipantResponse::from).collect(Collectors.toList());

        return RosterResponse.builder()
                .won(roster.isWon())
                .participants(participantRes)
                .build();
    }
}
