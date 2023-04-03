package com.menu.pubganalyzer.util.pubgAPI.exception;

public class PlayerNotFoundException extends PubgAPIException {
    public PlayerNotFoundException(String message) {
        super(message);
    }

    public PlayerNotFoundException() {
        super("해당 플레이어를 찾을 수 없습니다.");
    }
}
