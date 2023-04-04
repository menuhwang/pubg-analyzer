package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.model.enums.Shard;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchPlayerReq {
    private Shard shard;
    private String nickname;
}
