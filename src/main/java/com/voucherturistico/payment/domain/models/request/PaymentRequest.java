package com.voucherturistico.payment.domain.models.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voucherturistico.payment.domain.enums.PaymentMethodType;
import com.voucherturistico.payment.domain.enums.PaymentType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentRequest {

    private String entityId;

    @NotNull(message = "PaymentRequest 'amount' is required")
    private BigDecimal amount;

    @NotNull(message = "PaymentRequest 'paymentType' is required")
    private PaymentType paymentType;
    @NotNull(message = "PaymentRequest 'paymentMethod' is required")
    private PaymentMethodType paymentMethod;

    @NotNull(message = "PaymentRequest 'customerDocument' is required")
    private String customerDocument;
    @NotNull(message = "PaymentRequest 'customerName' is required")
    private String customerName;
    private String customerEmail;
    private String customerPhone;

    private String pixKey;

}
