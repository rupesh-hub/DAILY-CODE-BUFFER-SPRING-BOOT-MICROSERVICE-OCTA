package com.rupesh.paymentservice.service;

import com.rupesh.paymentservice.entity.TransactionDetail;
import com.rupesh.paymentservice.model.PaymentMode;
import com.rupesh.paymentservice.model.PaymentRequest;
import com.rupesh.paymentservice.model.PaymentResponse;
import com.rupesh.paymentservice.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentService implements IPaymentService {

    private final TransactionDetailRepository transactionDetailRepository;

    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        log.info("PaymentServic e.doPayment");
        TransactionDetail transactionDetail = TransactionDetail
                .builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .amount(paymentRequest.getAmount())
                .build();

        transactionDetail = transactionDetailRepository.save(transactionDetail);

        log.info("transaction completed successfully!!");

        return transactionDetail.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(String orderId) {
        TransactionDetail transactionDetail = transactionDetailRepository
                .findByOrderId(orderId)
                .orElseThrow();

        PaymentResponse paymentResponse =
                PaymentResponse.builder()
                        .paymentId(transactionDetail.getId())
                        .paymentMode(PaymentMode.valueOf(transactionDetail.getPaymentMode()))
                        .paymentDate(transactionDetail.getPaymentDate())
                        .orderId(transactionDetail.getOrderId())
                        .status(transactionDetail.getPaymentStatus())
                        .amount(transactionDetail.getAmount())
                        .build();

        return paymentResponse;
    }

}
