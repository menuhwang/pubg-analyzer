package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RewardDetailResponse {
    private int byPlayTime;
    private int byKills;
    private int byDamageDealt;
    private int byRanking;
    private int byModeScore;
    private int boostAmount;
}
