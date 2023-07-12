package com.menu.pubganalyzer.util.pubgAPI.exception;

public abstract class PubgAPIException extends RuntimeException {
    protected PubgAPIException(String message) {
        super(message);
    }
}
