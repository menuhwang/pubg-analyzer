package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeaponStatsResponse {
    private String weapon;
    @JsonProperty(value = "dBNODamage")
    private int dBNODamage;
    @JsonProperty(value = "dBNOHits")
    private int dBNOHits;
    private int damage;
    private List<HitDetailResponse> hitDetails;
    private int hits;
    private int holdingTime;
    private int shots;
}
