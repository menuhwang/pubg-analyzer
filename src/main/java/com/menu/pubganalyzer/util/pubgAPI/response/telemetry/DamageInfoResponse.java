package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DamageInfoResponse {
    private String damageReason;
    private String damageTypeCategory;
    private String damageCauserName;
    private List<String> additionalInfo;
    private float distance;
    private boolean isThroughPenetrableWall;

    public String getDamageReason() {
        return damageReason == null ? null : damageReason.toUpperCase();
    }

    public String getDamageTypeCategory() {
        return damageTypeCategory == null ? null : damageTypeCategory.toUpperCase();
    }

    public String getDamageCauserName() {
        return damageCauserName == null ? null : damageCauserName.replaceAll("-", "_").toUpperCase();
    }
}
