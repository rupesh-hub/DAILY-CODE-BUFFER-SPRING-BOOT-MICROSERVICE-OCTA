package com.rupesh.orderservice.service;

import com.rupesh.orderservice.entity.Order;
import com.rupesh.orderservice.external.client.ProductClient;
import com.rupesh.orderservice.external.response.PaymentResponse;
import com.rupesh.orderservice.model.OrderRequest;
import com.rupesh.orderservice.model.OrderResponse;
import com.rupesh.orderservice.model.OrderStatus;
import com.rupesh.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

import static java.util.Optional.of;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final RestTemplate restTemplate;

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
                        .build()
                )
                .orElse(null);
    }

    @Override
    public OrderResponse getOrderDetails(Long orderId) {


        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("order not found"));


        var productResponse = restTemplate.getForObject("http://PRODUCT-SERVICE/product/" + order.getProductId(), OrderResponse.ProductDetails.class);


        PaymentResponse paymentResponse = restTemplate.getForObject("http://PAYMENT-SERVICE/payment/order" + orderId, PaymentResponse.class);

        return OrderResponse
                .builder()
                .productId(order.getId())
                .quantity(order.getQuantity())
                .totalAmount(order.getAmount())
                .orderStatus(order.getOrderStatus())
                .productDetails(productResponse)
                .paymentDetails(
                        OrderResponse
                                .PaymentDetails
                                .builder()
                                .paymentId(paymentResponse.getPaymentId())
                                .paymentStatus(paymentResponse.getStatus())
                                .paymentMode(paymentResponse.getPaymentMode())
                                .paymentDate(paymentResponse.getPaymentDate())
                                .build()
                )
                .build();
    }


}
