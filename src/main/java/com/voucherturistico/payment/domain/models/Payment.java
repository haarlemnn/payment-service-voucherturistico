package com.voucherturistico.payment.domain.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.voucherturistico.payment.domain.enums.PaymentMethodType;
import com.voucherturistico.payment.domain.enums.PaymentStatus;
import com.voucherturistico.payment.domain.enums.PaymentType;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {

    private BigDecimal amount;

    private PaymentStatus status;
    private PaymentType paymentType;
    private PaymentMethodType paymentMethod;

    private String transactionId;
    private String paymentId;

}
