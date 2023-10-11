package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DamageInfoResponse {
    private String damageReason;
    private String damageTypeCategory;
    private String damageCauserName;
    private List<String> additionalInfo;
    private float distance;
    private Boolean isThroughPenetrableWall;

    public String getDamageReason() {
        return damageReason == null ? null : damageReason.toUpperCase();
    }

    public String getDamageTypeCategory() {
        return damageTypeCategory == null ? null : damageTypeCategory.toUpperCase();
    }

    public String getDamageCauserName() {
        return damageCauserName == null ? null : damageCauserName.replaceAll("-", "_").toUpperCase();
    }

    public boolean isThroughPenetrableWall() {
        return isThroughPenetrableWall;
    }

    public static DamageInfoResponse mappedBy(Object o) {
        if (Objects.isNull(o))
            return null;

        if (!(o instanceof Map)) {
            throw new IllegalArgumentException("Failed to map to DamageInfoResponse. Object is not of Map type.");
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) o;

        try {
            String damageReason = (String) map.get("damageReason");
            String damageTypeCategory = (String) map.get("damageTypeCategory");
            String damageCauserName = (String) map.get("damageCauserName");

            @SuppressWarnings("unchecked")
            List<String> additionalInfo = ((List<Object>) map.get("additionalInfo")).stream()
                    .map(item -> (String) item)
                    .collect(Collectors.toList());

            float distance = ((Number) map.get("distance")).floatValue();
            Boolean isThroughPenetrableWall = (Boolean) map.get("isThroughPenetrableWall");

            return DamageInfoResponse.builder()
                    .damageReason(damageReason)
                    .damageTypeCategory(damageTypeCategory)
                    .damageCauserName(damageCauserName)
                    .additionalInfo(additionalInfo)
                    .distance(distance)
                    .isThroughPenetrableWall(isThroughPenetrableWall)
                    .build();
        } catch (ClassCastException e) {
            System.err.println(map);
            throw e;
        }
    }
}
