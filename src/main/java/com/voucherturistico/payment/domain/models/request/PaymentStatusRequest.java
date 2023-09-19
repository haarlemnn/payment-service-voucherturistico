package com.voucherturistico.payment.domain.models.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voucherturistico.payment.domain.enums.PaymentMethodType;
import com.voucherturistico.payment.domain.enums.PaymentType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentStatusRequest {

    private String paymentId;

    @NotNull(message = "PaymentStatusRequest 'transactionId' is required")
    private String transactionId;

    @NotNull(message = "PaymentStatusRequest 'paymentType' is required")
    private PaymentType paymentType;

    @NotNull(message = "PaymentStatusRequest 'paymentMethod' is required")
    private PaymentMethodType paymentMethod;

    @NotNull(message = "PaymentStatusRequest 'providerSpecifics' is required")
    private Map<String, String> providerSpecifics;

}
