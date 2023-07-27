package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ItemResponse {
    private String itemId;
    private int stackCount;
    private String category;
    private String subCategory;
    private List<String> attachedItems;
}
