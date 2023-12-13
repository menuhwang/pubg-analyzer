package com.menu.pubganalyzer.util.pubg.response.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {
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
