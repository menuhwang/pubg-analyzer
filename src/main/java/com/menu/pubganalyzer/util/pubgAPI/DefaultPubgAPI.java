package com.menu.pubganalyzer.util.pubgAPI;

import com.menu.pubganalyzer.model.Asset;
import com.menu.pubganalyzer.model.Match;
import com.menu.pubganalyzer.model.Participant;
import com.menu.pubganalyzer.model.Roster;
import com.menu.pubganalyzer.model.enums.Shard;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class DefaultPubgAPI implements PubgAPI {
    private final RestTemplate restTemplate = new RestTemplate();

//    @Value("${util.pubg.api.token}")
//    private String TOKEN;

    private static final String BASE_URL = "https://api.pubg.com";
    private static Shard shard = Shard.STEAM;

    private static final HttpEntity DEFAULT_HTTP_ENTITY;

    static {
        HttpHeaders defaultHeaders = new HttpHeaders();
        defaultHeaders.set("accept", "application/vnd.api+json");

        DEFAULT_HTTP_ENTITY = new HttpEntity(defaultHeaders);
    }

    @Override
    public void setShard(Shard s) {
        shard = s;
    }

    @Override
    public Match match(String matchId) {
        String url = BASE_URL + "/shards/" + shard.name().toLowerCase() + "/matches/" + matchId;

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

        Match match = Match.of(data);

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
                        for (String participantId : r.getParticipants()) participants.get(participantId).setRoster(r);
                        rosters.add(r);
                    }
                    default -> {}
                }
            } catch (IllegalArgumentException ignore) {
            }
        }

        match.setAsset(assets.get(0));
        match.setParticipants(participants.values());
        match.setRosters(rosters);

        return match;
    }
}
