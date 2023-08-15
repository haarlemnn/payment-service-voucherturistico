package com.voucherturistico.payment.domain.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.voucherturistico.payment.domain.enums.PaymentStatus;
import com.voucherturistico.payment.domain.models.Payment;
import com.voucherturistico.payment.domain.models.PaymentRefund;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentStatusResponse {

    private Payment payment;
    private List<PaymentRefund> paymentRefund;

    private PaymentStatus status;

}
