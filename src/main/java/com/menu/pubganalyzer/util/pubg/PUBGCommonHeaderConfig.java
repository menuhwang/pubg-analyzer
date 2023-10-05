package com.menu.pubganalyzer.util.pubg;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PUBGCommonHeaderConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("accept", "application/vnd.api+json");
    }
}
