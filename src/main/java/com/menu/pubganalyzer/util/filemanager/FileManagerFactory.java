package com.menu.pubganalyzer.util.filemanager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Map;

@Slf4j
public class FileManagerFactory {
    public static FileManager getFileManager(String dir) {
        Map<String, String> env = System.getenv();
        String profile = env.getOrDefault("spring.profiles.active", "local");
//        if (profile.equals("prod")) return new S3FileManager(new File(dir));
        String path;
        if (profile.equals("local")) {
            path = dir;
        } else {
            path = ResourceUtils.FILE_URL_PREFIX + "pubg-analyzer" + File.separator + "data";
        }
        return new FileSystemManager(new File(path));
    }
}
