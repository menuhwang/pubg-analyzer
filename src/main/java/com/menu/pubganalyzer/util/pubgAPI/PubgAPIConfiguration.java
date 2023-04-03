package com.menu.pubganalyzer.util.pubgAPI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PubgAPIConfiguration {
    @Bean
    public PubgAPI pubgAPI(@Value("${util.pubg.api.token}") String token) {
        return new DefaultPubgAPI(token);
    }
}
