package com.rupesh.orderservice.resource;

import com.rupesh.orderservice.model.OrderRequest;
import com.rupesh.orderservice.model.OrderResponse;
import com.rupesh.orderservice.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/orders")
@RequiredArgsConstructor
public class OrderResource {

    private final IOrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody final OrderRequest request) {
        return new ResponseEntity(
                orderService.placeOrder(request),
                HttpStatus.CREATED
        );
    }

}
