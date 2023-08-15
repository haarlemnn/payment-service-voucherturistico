package com.voucherturistico.payment.domain.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentRefund {

    private BigDecimal amount;
    private String refundTransactionId;

}
