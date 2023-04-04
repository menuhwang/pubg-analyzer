package com.menu.pubganalyzer.domain.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Getter
@Entity
public class Asset {
    @Id
    private String id;
    private String name;
    private String description;
    private String url;
    private LocalDateTime createdAt;
    @OneToOne(fetch = FetchType.LAZY)
    private Match match;

    protected Asset() {
    }

    @Builder
    private Asset(String id, String name, String description, String url, LocalDateTime createdAt, Match match) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.createdAt = createdAt;
        this.match = match;
    }

    public static Asset of(Map<String, Object> raw, Match match) {
        String type = (String) raw.getOrDefault("type", null);
        if (type == null || !type.equals("asset")) throw new IllegalArgumentException("정상적인 매치 데이터를 입력해주세요.");

        Map<String, Object> attributes = (Map<String, Object>) raw.get("attributes");

        return Asset.builder()
                .id((String) raw.get("id"))
                .name((String) attributes.get("name"))
                .description((String) attributes.get("description"))
                .url((String) attributes.get("URL"))
                .createdAt(LocalDateTime.parse((String) attributes.get("createdAt"), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")))
                .match(match)
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
                ", match=" + match.getId() +
                '}';
    }
}
