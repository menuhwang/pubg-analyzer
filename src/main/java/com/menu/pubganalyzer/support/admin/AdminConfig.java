package com.menu.pubganalyzer.support.admin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminConfig {
    @Bean
    public AdminInterceptor adminInterceptor(AdminProperties adminProperties) {
        return new AdminInterceptor(adminProperties.getAllow());
    }
}
