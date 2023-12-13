package com.menu.pubganalyzer.common.dto.response;

public class ApiResult<T> {
    private final int status;
    private final boolean success;
    private final T result;

    public ApiResult(int status, boolean success, T result) {
        this.status = status;
        this.success = success;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getResult() {
        return result;
    }
}
