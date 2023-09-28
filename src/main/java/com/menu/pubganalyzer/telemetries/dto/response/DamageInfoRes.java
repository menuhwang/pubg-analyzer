package com.menu.pubganalyzer.telemetries.dto.response;

import com.menu.pubganalyzer.telemetries.dto.response.enums.DamageCauserName;
import com.menu.pubganalyzer.telemetries.dto.response.enums.DamageReason;
import com.menu.pubganalyzer.telemetries.dto.response.enums.DamageTypeCategory;
import com.menu.pubganalyzer.telemetries.model.DamageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class DamageInfoRes {
    private final DamageReason damageReason; // enum
    private final DamageTypeCategory damageTypeCategory; // enum
    private final DamageCauserName damageCauserName; // enum
    private final float distance;

    public static DamageInfoRes from(DamageInfo damageInfo) {
        if (Objects.isNull(damageInfo)) return null;
        return DamageInfoRes.builder()
                .damageReason(damageInfo.getDamageReason())
                .damageTypeCategory(damageInfo.getDamageTypeCategory())
                .damageCauserName(damageInfo.getDamageCauserName())
                .distance(damageInfo.getDistance())
                .build();
    }
}
