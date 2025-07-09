package com.msvc.hexagonal.zara.infrastructure.adapters.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
@Setter
@AllArgsConstructor
public class PriceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private HttpStatus errorCode;
    private String errorMessage;
}
