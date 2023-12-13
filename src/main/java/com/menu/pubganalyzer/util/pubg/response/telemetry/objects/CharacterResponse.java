package com.menu.pubganalyzer.util.pubg.response.telemetry.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterResponse {
    private String name;
    private int teamId;
    private float health;
    private LocationResponse location;
    private int ranking;
    private int individualRanking;
    private String accountId;
    private Boolean isInBlueZone;
    private Boolean isInRedZone;
    private List<String> zone;

    public boolean isInBlueZone() {
        return isInBlueZone;
    }

    public boolean isInRedZone() {
        return isInRedZone;
    }

    public static CharacterResponse mappedBy(Object o) {
        if (Objects.isNull(o))
            return null;

        if (!(o instanceof Map))
            throw new IllegalArgumentException("Failed map to character response. Object is not map type.");

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) o;

        try {
            String name = (String) map.get("name");
            int teamId = (int) map.get("teamId");
            float health = ((Number) map.get("health")).floatValue();
            int ranking = (int) map.get("ranking");
            int individualRanking = (int) map.get("individualRanking");
            String accountId = (String) map.get("accountId");
            Boolean isInBlueZone = (Boolean) map.get("isInBlueZone");
            Boolean isInRedZone = (Boolean) map.get("isInRedZone");
            @SuppressWarnings("unchecked")
            List<String> zone = (List<String>) map.get("zone");

            LocationResponse location = LocationResponse.mappedBy(map.get("location"));

            return CharacterResponse.builder()
                    .name(name)
                    .teamId(teamId)
                    .health(health)
                    .ranking(ranking)
                    .individualRanking(individualRanking)
                    .accountId(accountId)
                    .isInBlueZone(isInBlueZone)
                    .isInRedZone(isInRedZone)
                    .zone(zone)
                    .location(location)
                    .build();
        } catch (ClassCastException e) {
            System.err.println(map);
            throw e;
        }
    }
}
