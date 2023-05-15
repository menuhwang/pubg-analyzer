package com.menu.pubganalyzer.util.pubgAPI.response;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Delegate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class PlayersResponse {
    private List<Player> data;

    public List<Player> getPlayers() {
        return data;
    }

    @Getter
    @ToString
    public static class Player {
        String type;
        String id;
        Attribute attributes;
        Relationship relationships;
        Link links;

        public List<String> getMatchIds() {
            return relationships.getMatches().stream()
                    .map(Element::getId)
                    .collect(Collectors.toList());
        }
    }

    @Getter
    @ToString
    public static class Attribute {
        String name;
        String shardId;
        String stats;
        LocalDateTime createdAt;
        LocalDateTime updatedAt;
        String patchVersion;
        String titleId;
    }

    @Getter
    @ToString
    public static class Relationship {
        Included assets;
        Included matches;
    }

    @Getter
    @ToString
    public static class Included {
        @Delegate
        List<Element> data;
    }

    @Getter
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