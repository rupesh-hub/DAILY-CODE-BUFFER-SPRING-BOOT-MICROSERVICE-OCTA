package com.rupesh.paymentservice.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PaymentRequest {

    private long orderId;
    private long amount;
    private String referenceNumber;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

}
