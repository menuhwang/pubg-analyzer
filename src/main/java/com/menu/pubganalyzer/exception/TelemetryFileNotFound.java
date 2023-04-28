package com.menu.pubganalyzer.exception;

public class TelemetryFileNotFound extends AbstractApplicationException {
    public TelemetryFileNotFound() {
        super(ErrorCode.TELEMETRY_FILE_NOT_FOUND);
    }
}
