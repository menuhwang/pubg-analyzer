package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import lombok.*;

/**
* isGame = 0 -> Before lift off
* isGame = 0.1 -> On airplane
* isGame = 0.5 -> When there’s no ‘zone’ on map(before game starts)
* isGame = 1.0 -> First safezone and bluezone appear
* isGame = 1.5 -> First bluezone shrinks
* isGame = 2.0 -> Second bluezone appears
* isGame = 2.5 -> Second bluezone shrinks
**/

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommonResponse {
    private float isGame;
}
