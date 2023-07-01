package com.menu.pubganalyzer.domain.model;

import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/*
CREATE TABLE asset
        (
        `id`            CHAR(36)  NOT NULL,
        `name`          VARCHAR(255) NULL,
        `description` VARCHAR(255) NULL,
        `url`           CHAR(150) NULL,
        `created_at`    datetime     NULL,
        CONSTRAINT pk_asset PRIMARY KEY (id)
        );
 */

@Getter
@Entity(name = "asset")
public class Asset {
    @Id
    @Column(length = 36)
    private String id;
    private String name;
    private String description;
    @Column(length = 150)
    private String url;
    private LocalDateTime createdAt;

    protected Asset() {
    }

    @Builder
    private Asset(String id, String name, String description, String url, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.createdAt = createdAt;
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
