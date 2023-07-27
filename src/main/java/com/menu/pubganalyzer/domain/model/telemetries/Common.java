package com.menu.pubganalyzer.domain.model.telemetries;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.CommonResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class Common {
    private static final Common INSTANCE_INSTEAD_OF_NULL = Common.builder().isGame(0F).build();
    private final float isGame;

    public static Common from(CommonResponse common) {
        if (Objects.isNull(common)) return INSTANCE_INSTEAD_OF_NULL;

        return Common.builder()
                .isGame(common.getIsGame())
                .build();
    }
}
