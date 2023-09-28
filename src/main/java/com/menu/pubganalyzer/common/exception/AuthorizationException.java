package com.menu.pubganalyzer.common.exception;

import static com.menu.pubganalyzer.common.exception.ErrorCode.AUTHORIZATION;

public class AuthorizationException extends AbstractApplicationException {
    public AuthorizationException() {
        super(AUTHORIZATION);
    }
}
