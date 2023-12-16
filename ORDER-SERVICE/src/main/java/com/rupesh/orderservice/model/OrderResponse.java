package com.rupesh.orderservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("total_amount")
    private double totalAmount;

    private int quantity;

    @JsonProperty("payment_mode")
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @JsonProperty("order_status")
    private OrderStatus orderStatus;

    private ProductDetails productDetails;

    private PaymentDetails paymentDetails;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductDetails {

        private Long id;
        private String name;
        private double price;
        private int quantity;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @ToString
    @Builder
    public class PaymentDetails {

        private long paymentId;
        private String status;
        private PaymentMode paymentMode;
        private long amount;
        private Instant paymentDate;
        private long orderId;
        private String paymentStatus;
    }

}
