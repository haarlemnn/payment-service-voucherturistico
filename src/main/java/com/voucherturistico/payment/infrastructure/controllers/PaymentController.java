package com.voucherturistico.payment.infrastructure.controllers;

import com.voucherturistico.payment.application.PaymentServiceFactory;
import com.voucherturistico.payment.domain.models.request.CancelPaymentRequest;
import com.voucherturistico.payment.domain.models.request.PaymentRequest;
import com.voucherturistico.payment.domain.models.request.PaymentStatusRequest;
import com.voucherturistico.payment.domain.models.request.RefundPaymentRequest;
import com.voucherturistico.payment.domain.models.response.PaymentStatusResponse;
import com.voucherturistico.payment.domain.services.PaymentService;
import com.voucherturistico.payment.infrastructure.docs.CancelPaymentResponse;
import com.voucherturistico.payment.infrastructure.docs.DefaultAPIErrorResponse;
import com.voucherturistico.payment.infrastructure.docs.GetPaymentStatusResponse;
import com.voucherturistico.payment.infrastructure.docs.InitPaymentResponse;
import com.voucherturistico.payment.infrastructure.docs.RefundPaymentResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@Tag(name = "Payment")
public class PaymentController {

    private final PaymentServiceFactory paymentServiceFactory;

    @Autowired
    public PaymentController(PaymentServiceFactory paymentServiceFactory) {
        this.paymentServiceFactory = paymentServiceFactory;
    }

    @DefaultAPIErrorResponse()
    @InitPaymentResponse()
    @PostMapping("/init")
    public ResponseEntity<PaymentStatusResponse> initPayment(@RequestBody @Valid PaymentRequest paymentRequest) {
        PaymentService paymentService = paymentServiceFactory.getPaymentService(paymentRequest.getPaymentType());

        PaymentStatusResponse paymentStatusResponse = paymentService.initPayment(paymentRequest);

        return ResponseEntity.ok(paymentStatusResponse);
    }

    @DefaultAPIErrorResponse()
    @CancelPaymentResponse()
    @PostMapping("/cancel")
    public ResponseEntity<PaymentStatusResponse> cancelPayment(@RequestBody @Valid CancelPaymentRequest cancelPaymentRequest) {
        PaymentService paymentService = paymentServiceFactory.getPaymentService(cancelPaymentRequest.getPaymentType());

        PaymentStatusResponse paymentStatusResponse = paymentService.cancelPayment(cancelPaymentRequest);

        return ResponseEntity.ok(paymentStatusResponse);
    }

    @DefaultAPIErrorResponse()
    @GetPaymentStatusResponse()
    @PostMapping("/status")
    public ResponseEntity<PaymentStatusResponse> getPaymentStatus(@RequestBody @Valid PaymentStatusRequest paymentStatusRequest) {
        PaymentService paymentService = paymentServiceFactory.getPaymentService(paymentStatusRequest.getPaymentType());

        PaymentStatusResponse paymentStatusResponse = paymentService.readPayment(paymentStatusRequest);

        return ResponseEntity.ok(paymentStatusResponse);
    }

    @DefaultAPIErrorResponse()
    @RefundPaymentResponse()
    @PostMapping("/refund")
    public ResponseEntity<PaymentStatusResponse> refundPayment(@RequestBody @Valid RefundPaymentRequest refundPaymentRequest) {
        PaymentService paymentService = paymentServiceFactory.getPaymentService(refundPaymentRequest.getPaymentType());

        PaymentStatusResponse paymentStatusResponse = paymentService.refundPayment(refundPaymentRequest);

        return ResponseEntity.ok(paymentStatusResponse);
    }

}
