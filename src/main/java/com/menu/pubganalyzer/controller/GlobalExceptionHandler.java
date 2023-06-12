package com.menu.pubganalyzer.controller;

import com.menu.pubganalyzer.exception.MatchNotFoundException;
import com.menu.pubganalyzer.exception.ParticipantNotFoundException;
import com.menu.pubganalyzer.exception.PlayerNotFoundException;
import com.menu.pubganalyzer.support.apiResult.ApiResult;
import com.menu.pubganalyzer.support.apiResult.ApiResultUtil;
import com.menu.pubganalyzer.util.pubgAPI.exception.PubgAPIPlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @ExceptionHandler(value = {
            PubgAPIPlayerNotFoundException.class
    })
    public ResponseEntity<ApiResult<?>> playerNotFoundExceptionHandler(Throwable throwable) {
        return errorResponse(throwable, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler() {
        return "error/error";
    }

    private ResponseEntity<ApiResult<?>> errorResponse(Throwable throwable, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(ApiResultUtil.error(status.value(), throwable.getMessage()));
    }
}
