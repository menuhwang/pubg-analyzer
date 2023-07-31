package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameResultResponse {
    private int rank;
    private String gameResult;
    private int teamId;
    private StatsResponse stats;
    private String accountId;
}
