package com.menu.pubganalyzer.util.pubg.response.telemetry.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemResponse {
    private String itemId;
    private int stackCount;
    private String category;
    private String subCategory;
    private List<String> attachedItems;

    public static ItemResponse mappedBy(Object o) {
        if (Objects.isNull(o))
            return null;

        if (!(o instanceof Map))
            throw new IllegalArgumentException("Failed map to item response. Object is not map type.");

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) o;

        try {
            String itemId = (String) map.get("itemId");
            int stackCount = (int) map.get("stackCount");
            String category = (String) map.get("category");
            String subCategory = (String) map.get("subCategory");
            @SuppressWarnings("unchecked")
            List<String> attachedItems = (List<String>) map.get("attachedItems");

            return ItemResponse.builder()
                    .itemId(itemId)
                    .stackCount(stackCount)
                    .category(category)
                    .subCategory(subCategory)
                    .attachedItems(attachedItems)
                    .build();
        } catch (ClassCastException e) {
            System.err.println(map);
            throw e;
        }
    }
}
