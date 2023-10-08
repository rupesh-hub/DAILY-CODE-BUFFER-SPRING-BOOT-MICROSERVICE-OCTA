package com.rupesh.orderservice.configuration;

import com.rupesh.orderservice.external.decoder.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public ErrorDecoder customErrorDecoder() {
        return new CustomErrorDecoder();
    }

}
