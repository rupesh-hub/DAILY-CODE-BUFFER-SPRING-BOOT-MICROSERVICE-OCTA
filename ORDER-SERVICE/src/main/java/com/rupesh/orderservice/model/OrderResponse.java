package com.rupesh.orderservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
