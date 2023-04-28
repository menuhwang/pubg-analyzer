package com.menu.pubganalyzer.util.telemetryFileManager;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;
import java.util.Optional;

@Slf4j
public class DefaultTelemetryFileManager implements TelemetryFileManager {
    private final String path;

    public DefaultTelemetryFileManager(String path) {
        this.path = path;
    }

    @Override
    public boolean exists(String matchId, Class<?> type) {
        File file = new File(path + File.separator + matchId + File.separator + type.getSimpleName());
        return file.exists();
    }

    @Override
    public <T> Optional<List<T>> read(String matchId, Class<T> type) {
        File file = new File(path + File.separator + matchId + File.separator + type.getSimpleName());
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        List<T> result = null;
        try {
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            result = (List<T>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            log.warn("해당 파일을 찾을 수 없습니다. {}", file.getPath());
        } catch (IOException e) {
            log.error("객체 역직렬화 실패 [{}]", type.getName());
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (objectInputStream != null) objectInputStream.close();
                if (fileInputStream != null) fileInputStream.close();
            } catch (IOException e) {
                log.warn("스트림 close 실패");
            }
        }

        return Optional.ofNullable(result);
    }

    @Override
    public <T> boolean save(String matchId, List<T> obj) {
        File folder = new File(path + File.separator + matchId);
        if (!folder.exists()) folder.mkdirs();
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        boolean result = false;
        try {
            File file = new File(folder, obj.get(0).getClass().getSimpleName());
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
            result = true;
        } catch (IOException e) {
            log.error("객체 직렬화 실패 [{}]", obj.getClass().getName());
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (objectOutputStream != null) objectOutputStream.close();
                if (fileOutputStream != null) fileOutputStream.close();
            } catch (IOException e) {
                log.warn("스트림 close 실패");
            }
        }

        return result;
    }
}
