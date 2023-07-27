package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LocationResponse {
    private float x;
    private float y;
    private float z;
}
