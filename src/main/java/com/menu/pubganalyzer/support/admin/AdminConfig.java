package com.menu.pubganalyzer.support.admin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminConfig {
    private final AdminProperties adminProperties = new AdminProperties();

    @Bean
    public AdminInterceptor adminInterceptor() {
        return new AdminInterceptor(adminProperties.getAllow());
    }
}
