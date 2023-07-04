package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.model.enums.Shard;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SearchPlayerReq {
    private Shard shard;
    @NotBlank
    private String nickname;
}
