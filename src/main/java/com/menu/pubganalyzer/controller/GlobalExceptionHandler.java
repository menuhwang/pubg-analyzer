package com.menu.pubganalyzer.controller;

import com.menu.pubganalyzer.util.pubgAPI.exception.PubgAPIPlayerNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PubgAPIPlayerNotFoundException.class)
    public String playerNotFoundHandle() {
        return "/error/player-not-found";
    }
}
