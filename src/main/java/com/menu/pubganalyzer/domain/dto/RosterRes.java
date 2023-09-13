package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.model.matches.Roster;
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
public class RosterRes {
    private final boolean won;
    private final List<ParticipantRes> participants;

    public static RosterRes from(Roster roster) {
        List<ParticipantRes> participantRes = roster.getParticipants().stream().map(ParticipantRes::from).collect(Collectors.toList());

        return RosterRes.builder()
                .won(roster.isWon())
                .participants(participantRes)
                .build();
    }
}
