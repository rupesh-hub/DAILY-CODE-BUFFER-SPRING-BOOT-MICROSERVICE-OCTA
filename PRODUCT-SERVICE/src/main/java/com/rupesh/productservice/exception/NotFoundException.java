package com.rupesh.productservice.exception;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {

    private String errorCode;

    public NotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
