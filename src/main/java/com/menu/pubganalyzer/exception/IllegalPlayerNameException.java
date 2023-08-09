package com.menu.pubganalyzer.exception;

import static com.menu.pubganalyzer.exception.ErrorCode.ILLEGAL_PLAYER_NAME;

public class IllegalPlayerNameException extends AbstractApplicationException {
    public IllegalPlayerNameException() {
        super(ILLEGAL_PLAYER_NAME);
    }
}
