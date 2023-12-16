package com.rupesh.paymentservice.service;

import com.rupesh.paymentservice.model.PaymentRequest;
import com.rupesh.paymentservice.model.PaymentResponse;

public interface IPaymentService {
    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(String orderId);
}
