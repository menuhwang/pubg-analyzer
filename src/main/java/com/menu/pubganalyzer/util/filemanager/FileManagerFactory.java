package com.menu.pubganalyzer.util.filemanager;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class FileManagerFactory {
    public static FileManager getFileSystemManager(String path) {
        return new FileSystemManager(new File(path));
    }

//    public static FileManager getS3FileManager(String path) {
//        return new S3FileManager(new File(path));
//    }
}
