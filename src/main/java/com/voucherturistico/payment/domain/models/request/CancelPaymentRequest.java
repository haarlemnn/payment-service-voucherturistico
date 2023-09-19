package com.voucherturistico.payment.domain.models.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voucherturistico.payment.domain.enums.PaymentMethodType;
import com.voucherturistico.payment.domain.enums.PaymentType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelPaymentRequest {

    @NotNull(message = "CancelPaymentRequest 'paymentType' is required")
    private PaymentType paymentType;
    @NotNull(message = "CancelPaymentRequest 'paymentMethod' is required")
    private PaymentMethodType paymentMethod;

    @NotNull(message = "CancelPaymentRequest 'transactionId' is required")
    private String transactionId;
    private String paymentId;

    @NotNull(message = "CancelPaymentRequest 'providerSpecifics' is required")
    private Map<String, String> providerSpecifics;

}
