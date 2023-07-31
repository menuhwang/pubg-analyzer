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
public class StatsResponse {
    private int killCount;
    private float distanceOnFoot;
    private float distanceOnSwim;
    private float distanceOnVehicle;
    private float distanceOnParachute;
    private float distanceOnFreefall;
    private RewardDetailResponse bpRewardDetail;
    private RewardDetailResponse arcadeRewardDetail;
    private List<StatTrakDataPairResponse> statTrakDataPairs;
    private List<StatTrakDataPairResponse> headshotStatTrakDataPairs;
}
