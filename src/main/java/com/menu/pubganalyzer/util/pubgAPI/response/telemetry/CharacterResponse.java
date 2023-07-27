package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CharacterResponse {
    private String name;
    private int teamId;
    private float health;
    private LocationResponse location;
    private int ranking;
    private String accountId;
    private boolean isInBlueZone;
    private boolean isInRedZone;
    private List<String> zone;
}
