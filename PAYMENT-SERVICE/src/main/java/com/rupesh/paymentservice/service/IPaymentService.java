package com.rupesh.paymentservice.service;

import com.rupesh.paymentservice.model.PaymentRequest;

public interface IPaymentService {
    long doPayment(PaymentRequest paymentRequest);
}
