package com.rupesh.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rupesh.orderservice.model.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.Instant;

@Entity
@Table(name="orders")
@DynamicUpdate
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("order_id")
    private Long productId;

    private int quantity;

    @JsonProperty("order_date")
    private Instant orderDate;

    @JsonProperty("order_status")
    private OrderStatus orderStatus;

    private double amount;

}
