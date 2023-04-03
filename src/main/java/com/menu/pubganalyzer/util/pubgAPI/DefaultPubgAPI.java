package com.menu.pubganalyzer.util.pubgAPI;

import com.menu.pubganalyzer.model.*;
import com.menu.pubganalyzer.model.enums.Shard;
import com.menu.pubganalyzer.util.pubgAPI.exception.MatchNotFoundException;
import com.menu.pubganalyzer.util.pubgAPI.exception.PlayerNotFoundException;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

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
    public Match match(String matchId) {
        String url = BASE_URL + "/shards/" + shard.name().toLowerCase() + "/matches/" + matchId;
        Match match = null;
        try {
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, DEFAULT_HTTP_ENTITY, Map.class);

            Map<String, Object> data = (LinkedHashMap) response.getBody().get("data");
            List<Map<String, Object>> included = (ArrayList) response.getBody().get("included");
            included.sort((in1, in2) -> {
                String type1 = (String) in1.get("type");
                String type2 = (String) in2.get("type");

                int order1 = type1.equals("asset") ? 1 : type1.equals("participant") ? 2 : 3;
                int order2 = type2.equals("asset") ? 1 : type2.equals("participant") ? 2 : 3;

                return order1 - order2;
            });

            match = Match.of(data);

            Set<Roster> rosters = new HashSet<>();
            Map<String, Participant> participants = new HashMap<>();
            List<Asset> assets = new ArrayList<>();

            for (Map<String, Object> include : included) {
                try {
                    String type = (String) include.get("type");

                    switch (type) {
                        case "asset" -> assets.add(Asset.of(include, match));
                        case "participant" -> {
                            Participant p = Participant.of(include, match);
                            participants.put(p.getId(), p);
                        }
                        case "roster" -> {
                            Roster r = Roster.of(include, match);
                            for (String participantId : r.getParticipantIds())
                                participants.get(participantId).setRoster(r);
                            rosters.add(r);
                        }
                        default -> {
                        }
                    }
                } catch (IllegalArgumentException ignore) {
                }
            }

            match.setAsset(assets.get(0));
            match.setParticipants(participants.values());
            match.setRosters(rosters);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) throw new MatchNotFoundException(matchId);
        }

        return match;
    }

    @Override
    public Player player(String nickname) {
        String url = BASE_URL + "/shards/" + shard.name().toLowerCase() + "/players?filter[playerNames]=" + nickname;

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, AUTH_HTTP_ENTITY, Map.class);

        List<Map<String, Object>> data = (ArrayList) response.getBody().get("data");

        Player player = Player.of(data.get(0));

        if (player.getMatchIds().size() == 0) throw new PlayerNotFoundException();

        return player;
    }

    @Override
    public Set<Player> player(Collection<String> nicknames) {
        StringBuilder url = new StringBuilder(BASE_URL + "/shards/" + shard.name().toLowerCase() + "/players?filter[playerNames]=");
        for (String nickname : nicknames) {
            url.append(nickname);
            url.append(",");
        }

        url.deleteCharAt(url.length() - 1);

        ResponseEntity<Map> response = restTemplate.exchange(url.toString(), HttpMethod.GET, AUTH_HTTP_ENTITY, Map.class);

        List<Map<String, Object>> data = (ArrayList) response.getBody().get("data");

        return data.stream().map(Player::of).collect(Collectors.toSet());
    }
}
