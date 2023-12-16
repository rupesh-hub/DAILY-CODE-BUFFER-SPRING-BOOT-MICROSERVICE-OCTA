package com.rupesh.gateway.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackResource {

    @GetMapping("/orderServiceFallBack")
    public String orderServiceFallBack() {
        return "Order service is down!!";
    }

    @GetMapping("/paymentServiceFallBack")
    public String paymentServiceFallBack() {
        return "Payment service is down!!";
    }

    @GetMapping("/productServiceFallBack")
    public String productServiceFallBack() {
        return "Product service is down!!";
    }

}
