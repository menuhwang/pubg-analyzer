package com.menu.pubganalyzer.util.telemetryFileManager;

import java.util.List;
import java.util.Optional;

public interface TelemetryFileManager {
    boolean exists(String matchId, Class<?> type);

    <T> Optional<List<T>> read(String matchId, Class<T> type);

    <T> boolean save(String matchId, List<T> obj);
}
