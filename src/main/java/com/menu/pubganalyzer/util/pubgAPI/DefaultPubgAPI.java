package com.menu.pubganalyzer.util.pubgAPI;

import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.util.pubgAPI.exception.MatchNotFoundException;
import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.PlayersResponse;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

public class DefaultPubgAPI implements PubgAPI {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "https://api.pubg.com";
    private static Shard shard = Shard.STEAM;

    private final HttpEntity DEFAULT_HTTP_ENTITY;
    private final HttpEntity AUTH_HTTP_ENTITY;

    public DefaultPubgAPI(String token) {
        HttpHeaders defaultHeaders = new HttpHeaders();
        defaultHeaders.set("accept", "application/vnd.api+json");

        DEFAULT_HTTP_ENTITY = new HttpEntity(defaultHeaders);

        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.set("accept", "application/vnd.api+json");
        authHeaders.set("Authorization", "Bearer " + token);

        AUTH_HTTP_ENTITY = new HttpEntity(authHeaders);
    }

    @Override
    public void setShard(Shard s) {
        shard = s;
    }

    @Override
    public MatchResponse match(String matchId) {
        String url = BASE_URL + "/shards/" + shard.name().toLowerCase() + "/matches/" + matchId;
        MatchResponse matchResponse = null;
        try {
            ResponseEntity<MatchResponse> response = restTemplate.exchange(url, HttpMethod.GET, DEFAULT_HTTP_ENTITY, MatchResponse.class);
            matchResponse = response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) throw new MatchNotFoundException(matchId);
        }
        return matchResponse;
    }

    @Override
    public PlayersResponse player(Collection<String> nicknames) {
        StringBuilder url = new StringBuilder(BASE_URL + "/shards/" + shard.name().toLowerCase() + "/players?filter[playerNames]=");
        for (String nickname : nicknames) {
            url.append(nickname);
            url.append(",");
        }

        url.deleteCharAt(url.length() - 1);

        ResponseEntity<PlayersResponse> response = restTemplate.exchange(url.toString(), HttpMethod.GET, AUTH_HTTP_ENTITY, PlayersResponse.class);

        return response.getBody();
    }
}
