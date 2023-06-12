package com.menu.pubganalyzer.controller;

import com.menu.pubganalyzer.exception.MatchNotFoundException;
import com.menu.pubganalyzer.exception.ParticipantNotFoundException;
import com.menu.pubganalyzer.exception.PlayerNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {
            ParticipantNotFoundException.class,
            MatchNotFoundException.class,
            PlayerNotFoundException.class
    })
    public String badRequestExceptionHandler(Model model) {
        model.addAttribute("viewTitle", "잘못된 접근");
        return "error/bad-request";
    }
}
