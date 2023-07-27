package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GameResultResponse {
    private int rank;
    private String gameResult;
    private int teamId;
    private StatsResponse stats;
    private String accountId;
}
