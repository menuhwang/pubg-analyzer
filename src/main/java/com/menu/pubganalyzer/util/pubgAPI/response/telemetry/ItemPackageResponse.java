package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ItemPackageResponse {
    private String itemPackageId;
    private LocationResponse location;
    private List<ItemResponse> items;
}
