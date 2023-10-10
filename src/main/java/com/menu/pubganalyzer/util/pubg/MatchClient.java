package com.menu.pubganalyzer.util.pubg;

import com.menu.pubganalyzer.util.pubgAPI.response.match.MatchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "pubg-match",
        url = "https://api.pubg.com",
        configuration = {
                PUBGCommonHeaderConfig.class
        }
)
public interface MatchClient {
    @GetMapping(value = "/shards/{shard}/matches/{id}")
    MatchResponse fetchMatch(@PathVariable String shard, @PathVariable String id);
}
