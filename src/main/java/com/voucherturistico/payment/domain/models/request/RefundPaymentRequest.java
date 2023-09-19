package com.voucherturistico.payment.domain.models.request;

import com.voucherturistico.payment.domain.enums.PaymentMethodType;
import com.voucherturistico.payment.domain.enums.PaymentType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class RefundPaymentRequest {

    @NotNull(message = "RefundPaymentRequest 'paymentId' is required")
    private String paymentId;
    private String transactionId;

    @NotNull(message = "RefundPaymentRequest 'amount' is required")
    private BigDecimal amount;

    @NotNull(message = "RefundPaymentRequest 'paymentType' is required")
    private PaymentType paymentType;
    @NotNull(message = "RefundPaymentRequest 'paymentMethod' is required")
    private PaymentMethodType paymentMethod;

    @NotNull(message = "RefundPaymentRequest 'providerSpecifics' is required")
    private Map<String, String> providerSpecifics;

}
