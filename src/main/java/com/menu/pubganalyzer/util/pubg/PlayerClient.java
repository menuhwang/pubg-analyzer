package com.menu.pubganalyzer.util.pubg;

import com.menu.pubganalyzer.util.pubg.response.player.PlayersResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "pubg-player",
        url = "https://api.pubg.com",
        configuration = {
                PUBGCommonHeaderConfig.class
        }
)
public interface PlayerClient {
    @GetMapping(
            value = "/shards/{shard}/players",
            headers = "Authorization=Bearer ${util.pubg.api.token}"
    )
    PlayersResponse fetchPlayers(@PathVariable String shard, @RequestParam("filter[playerNames]") String playerNames);
}
