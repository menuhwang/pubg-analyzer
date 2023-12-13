package com.menu.pubganalyzer.util.pubg.response.match;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Data {
    String type;
    String id;
    Attribute attributes;
    Relationship relationships;
    Link links;
}
