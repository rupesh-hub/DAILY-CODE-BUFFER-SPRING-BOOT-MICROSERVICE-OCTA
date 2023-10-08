package com.rupesh.orderservice.exception;

import lombok.Data;

@Data
public class GlobalException extends RuntimeException {

    private String errorCode;
    private int status;

    public GlobalException(String message, String errorCode, int status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }

}
