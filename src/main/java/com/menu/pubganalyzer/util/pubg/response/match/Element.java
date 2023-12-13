package com.menu.pubganalyzer.util.pubg.response.match;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Element {
    String type;
    String id;
    Attribute attributes;
    Relationship relationships;
}
