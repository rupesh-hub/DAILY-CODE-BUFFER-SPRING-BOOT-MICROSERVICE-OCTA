package com.rupesh.orderservice.external.client;

import com.rupesh.orderservice.exception.GlobalException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE/products")
@CircuitBreaker(name="external", fallbackMethod = "fallback")
public interface ProductClient {

    @PutMapping("/reduceQuantity/{id}")
    ResponseEntity<Void> reduceQuantity(
            @PathVariable("id") final Long productId,
            @RequestParam("quantity") final int quantity
    );

    default void fallback(Exception exception){
        throw new GlobalException("Product service is not available!!", "UNAVAILABLE",500);
    }

}
