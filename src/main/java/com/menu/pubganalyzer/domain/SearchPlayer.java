package com.menu.pubganalyzer.domain;

import com.menu.pubganalyzer.domain.model.Participant;
import com.menu.pubganalyzer.domain.model.Player;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class SearchPlayer {
    private Player player;
    private Page<Participant> participants;

    public static SearchPlayer of(Player player, Page<Participant> participants) {
        SearchPlayer searchPlayer = new SearchPlayer();
        searchPlayer.setPlayer(player);
        searchPlayer.setParticipants(participants);
        return searchPlayer;
    }
}
