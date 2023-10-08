package com.rupesh.productservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {


    private String message;
    private HttpStatus status;

    @JsonProperty("error_code")
    private String errorCode;
    private LocalDateTime timestamp;

}
