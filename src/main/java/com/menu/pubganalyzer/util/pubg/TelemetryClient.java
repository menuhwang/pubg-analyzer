package com.menu.pubganalyzer.util.pubg;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "pubg-telemetry",
        url = "https://telemetry-cdn.pubg.com",
        configuration = {
                PUBGCommonHeaderConfig.class
        }
)
public interface TelemetryClient {
    String PUBG_TELEMETRY_CDN = "https://telemetry-cdn.pubg.com";

    @GetMapping(value = "{telemetryURL}")
    List<Object> fetchTelemetry(@PathVariable String telemetryURL);
}
