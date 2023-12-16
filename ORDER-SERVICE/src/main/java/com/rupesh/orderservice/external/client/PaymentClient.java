package com.rupesh.orderservice.external.client;

import com.rupesh.orderservice.exception.GlobalException;
import com.rupesh.orderservice.external.response.PaymentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name ="PAYMENT-SERVICE/payment")
@CircuitBreaker(name="external", fallbackMethod = "fallback")
public interface PaymentClient {

    @PostMapping
    ResponseEntity<Long> doPayment(@RequestBody PaymentResponse response);

    default void fallback(Exception exception){
        throw new GlobalException("Payment service is not available!!", "UNAVAILABLE",500);
    }

}
