package com.menu.pubganalyzer.support.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AdminConfig {
    private final AdminProperties adminProperties;

    @Bean
    public AdminInterceptor adminInterceptor() {
        return new AdminInterceptor(adminProperties.getAllow());
    }
}
