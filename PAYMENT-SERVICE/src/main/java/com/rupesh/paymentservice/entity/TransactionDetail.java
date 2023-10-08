package com.rupesh.paymentservice.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "transactions_details")
public class TransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "payment_mode")
    private String paymentMode;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "payment_date")
    private Instant paymentDate;

    @Column(name = "status")
    private String paymentStatus;

    @Column(name = "amount")
    private long amount;

}
