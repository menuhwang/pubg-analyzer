package com.menu.pubganalyzer.exception;

public class ServerException extends AbstractApplicationException {
    public ServerException() {
        super(ErrorCode.SERVER_ERROR);
    }

    public ServerException(Throwable e) {
        super(ErrorCode.SERVER_ERROR, e);
    }
}
