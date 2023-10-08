package com.rupesh.orderservice.external.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rupesh.orderservice.exception.GlobalException;
import com.rupesh.orderservice.external.response.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();

        log.info("::{}", response.request().url());
        log.info("::{}", response.request().headers());


        try {
            ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
            log.error("::{}", errorResponse.getMessage());
        } catch (Exception e) {
            log.error("::{}", e.getMessage());
            throw new GlobalException(e.getMessage(),"INTERNAL_SERVER_ERROR",500);
        }

        return null;

    }

}
