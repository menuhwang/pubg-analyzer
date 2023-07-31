package com.menu.pubganalyzer.util.filemanager;

public interface FileManager {
    void saveJson(String filename, String json);
    String readJson(String filename);
    boolean exists(String path);
    boolean delete(String path);
}
