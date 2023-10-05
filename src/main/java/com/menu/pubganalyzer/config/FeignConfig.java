package com.menu.pubganalyzer.config;

import com.menu.pubganalyzer.util.pubg.MatchClient;
import com.menu.pubganalyzer.util.pubg.PlayerClient;
import com.menu.pubganalyzer.util.pubg.TelemetryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(
        clients = {
                MatchClient.class,
                PlayerClient.class,
                TelemetryClient.class,
        }
)
public class FeignConfig {
}
