package com.menu.pubganalyzer.support.admin;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "application.admin")
public class AdminProperties {
    private List<String> allow = List.of("127.0.0.1", "localhost");

    public List<String> getAllow() {
        return allow;
    }

    public void setAllow(List<String> allow) {
        this.allow = allow;
    }
}
