package com.menu.pubganalyzer.util.pubg.response.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Delegate;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"empty"})
public class Included {
    @Delegate
    List<Element> data;
}
