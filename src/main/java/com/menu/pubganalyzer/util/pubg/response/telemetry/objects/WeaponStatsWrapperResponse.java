package com.menu.pubganalyzer.util.pubg.response.telemetry.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeaponStatsWrapperResponse {
    private String accountId;
    private List<WeaponStatsResponse> stats;

    public static WeaponStatsWrapperResponse mappedBy(Object o) {
        if (Objects.isNull(o))
            return null;

        if (!(o instanceof Map)) {
            throw new IllegalArgumentException("Failed to map to WeaponStatsWrapperResponse. Object is not of Map type.");
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) o;

        try {
            String accountId = (String) map.get("accountId");

            @SuppressWarnings("unchecked")
            List<WeaponStatsResponse> stats = ((List<Object>) map.get("stats")).stream()
                    .map(WeaponStatsResponse::mappedBy)
                    .collect(Collectors.toList());

            return WeaponStatsWrapperResponse.builder()
                    .accountId(accountId)
                    .stats(stats)
                    .build();
        } catch (ClassCastException e) {
            System.err.println(map);
            throw e;
        }
    }
}
