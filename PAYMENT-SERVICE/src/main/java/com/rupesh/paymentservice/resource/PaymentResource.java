package com.rupesh.paymentservice.resource;

import com.rupesh.paymentservice.model.PaymentRequest;
import com.rupesh.paymentservice.model.PaymentResponse;
import com.rupesh.paymentservice.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        "/payments"
)
@RequiredArgsConstructor
public class PaymentResource {

    private final IPaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody final PaymentRequest paymentRequest){
        return ResponseEntity.ok(paymentService.doPayment(paymentRequest));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetails(@PathVariable final String orderId){
        return ResponseEntity.ok(paymentService.getPaymentDetailsByOrderId(orderId));
    }


}
