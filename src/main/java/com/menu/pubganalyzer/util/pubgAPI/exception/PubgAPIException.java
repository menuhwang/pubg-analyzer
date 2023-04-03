package com.menu.pubganalyzer.util.pubgAPI.exception;

abstract class PubgAPIException extends RuntimeException {
    protected PubgAPIException(String message) {
        super(message);
    }
}
