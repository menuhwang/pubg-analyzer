package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class HitDetailResponse {
    private String bodyPart;
    @JsonProperty(value = "dBNOs")
    private int dBNOs;
    @JsonProperty(value = "dBNOHits")
    private int dBNOHits;
    @JsonProperty(value = "dBNODamage")
    private int dBNODamage;
    private int kills;
    private int hits;
    private int damage;
}
