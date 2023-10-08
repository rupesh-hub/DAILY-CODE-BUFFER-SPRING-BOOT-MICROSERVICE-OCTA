package com.rupesh.orderservice.service;

import com.rupesh.orderservice.external.client.ProductClient;
import com.rupesh.orderservice.entity.Order;
import com.rupesh.orderservice.model.OrderRequest;
import com.rupesh.orderservice.model.OrderResponse;
import com.rupesh.orderservice.model.OrderStatus;
import com.rupesh.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static java.util.Optional.of;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    @Override
    public OrderResponse placeOrder(final OrderRequest request) {

        log.info("placing order request: {}", request);

        productClient.reduceQuantity(request.getProductId(), request.getQuantity());

        log.info("order placed: {}", request);

        return of(
                        orderRepository
                                .save(Order
                                        .builder()
                                        .productId(request.getProductId())
                                        .amount(request.getTotalAmount())
                                        .orderStatus(OrderStatus.CREATED)
                                        .orderDate(Instant.now())
                                        .quantity(request.getQuantity())
                                        .build())
                )
                .map(order -> OrderResponse
                        .builder()
                        .productId(order.getId())
                        .quantity(order.getQuantity())
                        .totalAmount(order.getAmount())
                        .orderStatus(order.getOrderStatus())
                        .build())
                .orElse(null);
    }

}
