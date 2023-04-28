package com.menu.pubganalyzer.util.telemetryFileManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;

@Configuration
public class TelemetryFileManagerConfiguration {
    private static final String FOLDER = "telemetry";

    @Bean
    public TelemetryFileManager telemetryFileManager(ResourceLoader resourceLoader) throws IOException {
        String root = resourceLoader.getResource("file:").getFile().getAbsolutePath();
        return new DefaultTelemetryFileManager(root + File.separator + FOLDER);
    }
}
