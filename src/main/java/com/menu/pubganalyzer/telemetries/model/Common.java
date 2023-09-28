package com.menu.pubganalyzer.telemetries.model;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.CommonResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Common {
    private static final Common INSTANCE_INSTEAD_OF_NULL = Common.builder().isGame(0F).build();
    private float isGame;

    public static Common from(CommonResponse common) {
        if (Objects.isNull(common)) return INSTANCE_INSTEAD_OF_NULL;

        return Common.builder()
                .isGame(common.getIsGame())
                .build();
    }
}
