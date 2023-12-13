package com.menu.pubganalyzer.common.exception;

import static com.menu.pubganalyzer.common.exception.ErrorCode.ILLEGAL_PLAYER_NAME;

public class IllegalPlayerNameException extends AbstractApplicationException {
    public IllegalPlayerNameException() {
        super(ILLEGAL_PLAYER_NAME);
    }
}
