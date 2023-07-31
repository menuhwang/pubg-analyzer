package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

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
}
