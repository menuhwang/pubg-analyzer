package com.menu.pubganalyzer.exception;

import static com.menu.pubganalyzer.exception.ErrorCode.AUTHORIZATION;

public class AuthorizationException extends AbstractApplicationException {
    public AuthorizationException() {
        super(AUTHORIZATION);
    }
}
