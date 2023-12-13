package com.menu.pubganalyzer.util.pubg.response.telemetry.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Map;
import java.util.Objects;

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

    public static GameResultResponse mappedBy(Object o) {
        if (Objects.isNull(o))
            return null;

        if (!(o instanceof Map)) {
            throw new IllegalArgumentException("Failed to map to GameResultResponse. Object is not of Map type.");
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) o;

        try {
            int rank = (int) map.get("rank");
            String gameResult = (String) map.get("gameResult");
            int teamId = (int) map.get("teamId");
            StatsResponse stats = StatsResponse.mappedBy(map.get("stats"));
            String accountId = (String) map.get("accountId");

            return GameResultResponse.builder()
                    .rank(rank)
                    .gameResult(gameResult)
                    .teamId(teamId)
                    .stats(stats)
                    .accountId(accountId)
                    .build();
        } catch (ClassCastException e) {
            System.err.println(map);
            throw e;
        }
    }

}
