package com.menu.pubganalyzer.util.pubgAPI.response.player;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Relationship {
    Included assets;
    Included matches;
}
