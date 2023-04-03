package com.menu.pubganalyzer.controller;

import com.menu.pubganalyzer.util.pubgAPI.exception.PlayerNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PlayerNotFoundException.class)
    public String playerNotFoundHandle() {
        return "/error/player-not-found";
    }
}
