package com.voucherturistico.payment.domain.services;

import com.voucherturistico.payment.domain.enums.PaymentType;
import com.voucherturistico.payment.domain.models.request.CancelPaymentRequest;
import com.voucherturistico.payment.domain.models.request.PaymentRequest;
import com.voucherturistico.payment.domain.models.request.PaymentStatusRequest;
import com.voucherturistico.payment.domain.models.request.RefundPaymentRequest;
import com.voucherturistico.payment.domain.models.response.PaymentStatusResponse;

public interface PaymentService {

    PaymentStatusResponse initPayment(PaymentRequest paymentRequest);
    PaymentStatusResponse readPayment(PaymentStatusRequest paymentStatusRequest);
    PaymentStatusResponse cancelPayment(CancelPaymentRequest cancelPaymentRequest);
    PaymentStatusResponse refundPayment(RefundPaymentRequest refundPaymentRequest);
    PaymentType type();

}
