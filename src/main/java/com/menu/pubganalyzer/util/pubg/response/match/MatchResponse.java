package com.menu.pubganalyzer.util.pubg.response.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Delegate;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"meta"})
public class MatchResponse {
    @Delegate
    private Data data;
    private Link links;
    private List<Element> included;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Data {
        String type;
        String id;
        Attribute attributes;
        Relationship relationships;
        Link links;
    }
}
