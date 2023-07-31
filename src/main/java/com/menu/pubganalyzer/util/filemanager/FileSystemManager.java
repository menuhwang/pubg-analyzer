package com.menu.pubganalyzer.util.filemanager;

import com.menu.pubganalyzer.exception.ServerException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class FileSystemManager implements FileManager {
    private final static String JSON_EXT = ".json";
    private final File dir;

    public FileSystemManager(File dir) {
        if (dir.mkdirs()) log.info("created directory [{}]", dir.getPath());
        this.dir = dir;
    }

    @Override
    public void saveJson(String filename, String json) {
        try (
                FileWriter fw = new FileWriter(new File(dir, filename + JSON_EXT));
                BufferedWriter bw = new BufferedWriter(fw)
        ) {
            bw.write(json);
            bw.flush();
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override
    public String readJson(String filename) {
        StringBuilder sb = new StringBuilder();
        try (
                FileReader fr = new FileReader(new File(dir, filename + JSON_EXT));
                BufferedReader br = new BufferedReader(fr);
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            throw new ServerException(e);
        }

        return sb.toString();
    }

    @Override
    public boolean exists(String filename) {
        File file = new File(dir, filename);
        return file.exists();
    }

    @Override
    public boolean delete(String filename) {
        File file = new File(dir, filename);
        return file.delete();
    }
}
