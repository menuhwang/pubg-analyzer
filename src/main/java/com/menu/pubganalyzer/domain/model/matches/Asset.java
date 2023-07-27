package com.menu.pubganalyzer.domain.model.matches;

import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class Asset {
    private String id;
    private String name;
    private String description;
    private String url;
    private LocalDateTime createdAt;

    protected Asset() {
    }

    public static Asset of(MatchResponse.Element asset) {
        return Asset.builder()
                .id(asset.getId())
                .name(asset.getAttributes().getName())
                .description(asset.getAttributes().getDescription())
                .url(asset.getAttributes().getURL())
                .createdAt(asset.getAttributes().getCreatedAt())
                .build();
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
