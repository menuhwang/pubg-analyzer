package com.menu.pubganalyzer.config;

import com.menu.pubganalyzer.util.filemanager.FileManager;
import com.menu.pubganalyzer.util.filemanager.FileManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileManagerConfig {
    private static final String TELEMETRY_PATH = "telemetries";

    @Bean
    public FileManager telemetryFileManager() {
        return FileManagerFactory.getFileManager(TELEMETRY_PATH);
    }
}
