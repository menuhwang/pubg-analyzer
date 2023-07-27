package com.menu.pubganalyzer.util.pubgAPI.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Delegate;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlayersResponse {
    private List<Player> data;

    @JsonIgnore
    public List<Player> getPlayers() {
        return data;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Player {
        String type;
        String id;
        Attribute attributes;
        Relationship relationships;
        Link links;

        @JsonIgnore
        public List<String> getMatchIds() {
            return relationships.getMatches().stream()
                    .map(Element::getId)
                    .collect(Collectors.toList());
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Attribute {
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

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Relationship {
        Included assets;
        Included matches;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @JsonIgnoreProperties({"empty"})
    public static class Included {
        @Delegate
        List<Element> data;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Element {
        String type;
        String id;
    }

    @Getter
    @ToString
    public static class Link {
        String schema;
        String self;
    }
}
