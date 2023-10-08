package com.rupesh.orderservice.exception;

import com.rupesh.orderservice.external.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(final GlobalException ex) {
        return new ResponseEntity(
                ErrorResponse
                        .builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND)
                        .errorCode(ex.getErrorCode())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.valueOf(ex.getStatus())
        );
    }

}
