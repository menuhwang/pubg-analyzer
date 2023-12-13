package com.menu.pubganalyzer.telemetries.dto.response;

import com.menu.pubganalyzer.telemetries.dto.response.enums.DamageCauserName;
import com.menu.pubganalyzer.telemetries.dto.response.enums.DamageReason;
import com.menu.pubganalyzer.telemetries.dto.response.enums.DamageTypeCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class DamageInfoResponse {
    private final DamageReason damageReason; // enum
    private final DamageTypeCategory damageTypeCategory; // enum
    private final DamageCauserName damageCauserName; // enum
    private final float distance;

    public static DamageInfoResponse from(com.menu.pubganalyzer.util.pubg.response.telemetry.objects.DamageInfoResponse damageInfo) {
        if (Objects.isNull(damageInfo)) return null;
        return DamageInfoResponse.builder()
                .damageReason(DamageReason.of(damageInfo.getDamageReason()))
                .damageTypeCategory(DamageTypeCategory.of(damageInfo.getDamageTypeCategory()))
                .damageCauserName(DamageCauserName.of(damageInfo.getDamageCauserName()))
                .distance(damageInfo.getDistance())
                .build();
    }
}
