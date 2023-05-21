package com.menu.pubganalyzer.support.apiResult;

import java.util.Map;

public class ApiResultUtil {
    public static <T> ApiResult<T> success(T result) {
        return new ApiResult<>(200, true, result);
    }

    public static <T> ApiResult<T> success(int status, T result) {
        return new ApiResult<>(status, true, result);
    }

    public static ApiResult<Map<String, String>> success(String message) {
        return new ApiResult<>(200, true, Map.of("message", message));
    }

    public static ApiResult<Map<String, String>> success(int status, String message) {
        return new ApiResult<>(status, true, Map.of("message", message));
    }
}
