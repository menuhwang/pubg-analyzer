package com.menu.pubganalyzer.exception;

public class PlayerNotFoundException extends AbstractApplicationException {
    public PlayerNotFoundException() {
        super(ErrorCode.PLAYER_NOT_FOUND);
    }
}
