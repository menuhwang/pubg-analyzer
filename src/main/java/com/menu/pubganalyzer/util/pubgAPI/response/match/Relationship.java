package com.menu.pubganalyzer.util.pubgAPI.response.match;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Relationship {
    Included rosters;
    Included assets;
    Included participants;
    Included team;
}
