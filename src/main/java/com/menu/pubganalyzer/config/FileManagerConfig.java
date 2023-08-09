package com.menu.pubganalyzer.config;

import com.menu.pubganalyzer.util.filemanager.FileManager;
import com.menu.pubganalyzer.util.filemanager.FileManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.ResourceUtils;

import java.io.File;

@Configuration
public class FileManagerConfig {
    private static final String TELEMETRY_PATH = "telemetries";

    @Bean("telemetryFileManager")
    public FileManager telemetryFileManagerDefault() {
        return FileManagerFactory.getFileSystemManager(TELEMETRY_PATH);
    }

    @Bean("telemetryFileManager")
    @Profile("prod")
    public FileManager telemetryFileManagerProd() {
        String path = ResourceUtils.FILE_URL_PREFIX + "pubg-analyzer" + File.separator + "data";
        return FileManagerFactory.getFileSystemManager(path);
    }

//    @Bean("telemetryFileManager")
//    public FileManager telemetryFileManagerS3() {
//        return FileManagerFactory.getS3FileManager(TELEMETRY_PATH);
//    }
}
