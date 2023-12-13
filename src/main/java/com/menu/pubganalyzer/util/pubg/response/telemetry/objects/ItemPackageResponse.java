package com.menu.pubganalyzer.util.pubg.response.telemetry.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemPackageResponse {
    private String itemPackageId;
    private LocationResponse location;
    private List<ItemResponse> items;

    public static ItemPackageResponse mappedBy(Object o) {
        if (Objects.isNull(o))
            return null;

        if (!(o instanceof Map))
            throw new IllegalArgumentException("Failed map to character response. Object is not map type.");

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) o;

        try {
            String itemPackageId = (String) map.get("itemPackageId");
            LocationResponse location = LocationResponse.mappedBy(map.get("location"));
            @SuppressWarnings("unchecked")
            List<ItemResponse> items = ((List<Object>) map.get("items")).stream()
                    .map(ItemResponse::mappedBy)
                    .collect(Collectors.toList());

            return ItemPackageResponse.builder()
                    .itemPackageId(itemPackageId)
                    .location(location)
                    .items(items)
                    .build();
        } catch (ClassCastException e) {
            System.err.println(map);
            throw e;
        }
    }
}
