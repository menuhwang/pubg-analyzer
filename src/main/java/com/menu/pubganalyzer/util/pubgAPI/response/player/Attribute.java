package com.menu.pubganalyzer.util.pubgAPI.response.player;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Attribute {
    String name;
    String shardId;
    String stats;
    String patchVersion;
    String titleId;
    String clanId;
    String banType;

    public String getShardId() {
        return shardId == null ? null : shardId.toUpperCase();
    }

    public String getTitleId() {
        return titleId == null ? null : titleId.toUpperCase();
    }

    public String getBanType() {
        return banType == null ? null : banType.toUpperCase();
    }
}
