package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StatsResponse {
    private int killCount;
    private float distanceOnFoot;
    private float distanceOnSwim;
    private float distanceOnVehicle;
    private float distanceOnParachute;
    private float distanceOnFreefall;
}
