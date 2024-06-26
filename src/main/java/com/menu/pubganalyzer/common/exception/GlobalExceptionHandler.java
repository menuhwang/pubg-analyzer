package com.menu.pubganalyzer.common.exception;

import com.menu.pubganalyzer.common.dto.response.ApiResult;
import com.menu.pubganalyzer.common.dto.response.ApiResultUtil;
import com.menu.pubganalyzer.util.pubg.exception.PubgAPIException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ServerException.class)
    public ResponseEntity<ApiResult<?>> serverExceptionHandler(ServerException serverException) {
        Throwable cause = serverException.getCause();
        log.error("{}", cause.getMessage(), cause);

        return errorResponse(serverException, serverException.getHttpStatus());
    }

    @ExceptionHandler(value = AbstractApplicationException.class)
    public ResponseEntity<ApiResult<?>> badRequestExceptionHandler(AbstractApplicationException e) {
        log.warn("{}", e.getMessage(), e);

        return errorResponse(e, e.getHttpStatus());
    }

    @ExceptionHandler(value = PubgAPIException.class)
    public ResponseEntity<ApiResult<?>> pubgAPIExceptionHandler(PubgAPIException e) {
        log.warn("{}", e.getMessage(), e);

        return errorResponse(e, e.getHttpStatus());
    }

    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<ApiResult<?>> validationExceptionHandler(BindException e) {
        log.warn("{}", e.getMessage(), e);

        return errorResponse(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResult<?>> exceptionHandler(Exception e) {
        log.error("{}", e.getMessage(), e);

        return errorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ApiResult<?>> errorResponse(Throwable throwable, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(ApiResultUtil.error(status.value(), throwable.getMessage()));
    }
}
