package com.menu.pubganalyzer.util.pubgAPI;

import com.menu.pubganalyzer.util.pubgAPI.exception.PubgAPIMatchNotFoundException;
import com.menu.pubganalyzer.util.pubgAPI.exception.PubgAPIPlayerNotFoundException;
import com.menu.pubganalyzer.util.pubgAPI.response.match.MatchResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.player.PlayersResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
public class DefaultPubgAPI implements PubgAPI {
    private final RestTemplate restTemplate;
    private static final String BASE_URL = "https://api.pubg.com";

    private final HttpEntity<MultiValueMap<String, String>> DEFAULT_HTTP_ENTITY;
    private final HttpEntity<MultiValueMap<String, String>> AUTH_HTTP_ENTITY;

    public DefaultPubgAPI(String token, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        HttpHeaders defaultHeaders = new HttpHeaders();
        defaultHeaders.set("accept", "application/vnd.api+json");

        DEFAULT_HTTP_ENTITY = new HttpEntity<>(defaultHeaders);

        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.set("accept", "application/vnd.api+json");
        authHeaders.set("Authorization", "Bearer " + token);

        AUTH_HTTP_ENTITY = new HttpEntity<>(authHeaders);
    }

    @Override
    @Cacheable(cacheNames = "match", key = "#matchId")
    public MatchResponse match(String shardId, String matchId) {
        String url = BASE_URL + "/shards/" + shardId.toLowerCase() + "/matches/" + matchId;
        MatchResponse matchResponse = null;
        try {
            ResponseEntity<MatchResponse> response = restTemplate.exchange(url, HttpMethod.GET, DEFAULT_HTTP_ENTITY, MatchResponse.class);
            matchResponse = response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new PubgAPIMatchNotFoundException(matchId);
            } else {
                log.warn("you need to handle exception");
                log.error("{}", e.getMessage(), e);
            }
        }
        return matchResponse;
    }

    @Override
    @Cacheable(cacheNames = "player", key = "#nickname")
    public PlayersResponse player(String shardId, String nickname) throws PubgAPIPlayerNotFoundException {
        StringBuilder url = new StringBuilder(BASE_URL);
        url.append("/shards/")
                .append(shardId.toLowerCase())
                .append("/players?filter[playerNames]=")
                .append(nickname);

        PlayersResponse playersResponse = null;
        try {
            ResponseEntity<PlayersResponse> response = restTemplate.exchange(url.toString(), HttpMethod.GET, AUTH_HTTP_ENTITY, PlayersResponse.class);
            playersResponse = response.getBody();
        } catch (HttpClientErrorException e) {
            switch (e.getStatusCode()) {
                case NOT_FOUND:
                    throw new PubgAPIPlayerNotFoundException();
                case TOO_MANY_REQUESTS:
                    log.warn("you have exceeded the number of available requests");
                    break;
                default:
                    log.warn("you need to handle exception");
                    log.error("{}", e.getMessage(), e);
                    break;
            }
        }

        return playersResponse;
    }

    @Override
    @Cacheable(cacheNames = "telemetry")
    public List<TelemetryResponse> telemetry(String url) {
        ParameterizedTypeReference<List<TelemetryResponse>> responseType = new ParameterizedTypeReference<>() {
        };
        return restTemplate.exchange(url, HttpMethod.GET, DEFAULT_HTTP_ENTITY, responseType).getBody();
    }
}
