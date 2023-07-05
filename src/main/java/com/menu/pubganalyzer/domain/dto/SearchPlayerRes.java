package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.SearchPlayer;
import com.menu.pubganalyzer.domain.model.Participant;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class SearchPlayerRes {
    private final String player;
    private final PageDTO<ParticipantRes> matches;

    private SearchPlayerRes(String player, PageDTO<ParticipantRes> matches) {
        this.player = player;
        this.matches = matches;
    }

    public static SearchPlayerRes of(SearchPlayer searchPlayer) {
        Page<Participant> participantPage = searchPlayer.getParticipants();

        return new SearchPlayerRes(
                searchPlayer.getPlayer().getName(),
                PageDTO.of(participantPage.map(ParticipantRes::of), participantPage.getPageable().getPageSize())
        );
    }
}
