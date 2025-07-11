package com.msvc.hexagonal.zara.infrastructure.adapters.exception;

import com.msvc.hexagonal.zara.infrastructure.util.ConstantsUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PriceException.class)
    public ResponseEntity<String> handlePriceException(PriceException ex) {
        HttpStatus status = ex.getErrorCode() != null ? ex.getErrorCode() : HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(ex.getErrorMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ConstantsUtils.UNEXPECTEDERROR);
    }
}