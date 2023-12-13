package com.menu.pubganalyzer.util.pubg.response.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlayersResponse {
    private List<Player> data;

    @JsonIgnore
    public List<Player> getPlayers() {
        return data;
    }
}
