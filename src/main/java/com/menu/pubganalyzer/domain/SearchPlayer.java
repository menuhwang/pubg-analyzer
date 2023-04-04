package com.menu.pubganalyzer.domain;

import com.menu.pubganalyzer.domain.model.Participant;
import com.menu.pubganalyzer.domain.model.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchPlayer {
    private Player player;
    private List<Participant> participants;
}
