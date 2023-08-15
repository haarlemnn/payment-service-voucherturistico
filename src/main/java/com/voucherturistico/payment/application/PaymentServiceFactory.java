package com.voucherturistico.payment.application;

import com.voucherturistico.payment.domain.enums.PaymentType;
import com.voucherturistico.payment.domain.services.PaymentService;
import com.voucherturistico.payment.infrastructure.http.bb.BBPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentServiceFactory {

    private final ApplicationContext applicationContext;

    @Autowired
    public PaymentServiceFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private static final Map<PaymentType, Class<? extends PaymentService>> paymentServiceTypeMap = new HashMap<>();

    static {
        paymentServiceTypeMap.put(PaymentType.BB, BBPaymentService.class);
    }

    public PaymentService getPaymentService(PaymentType paymentType) {
        Class<? extends PaymentService> paymentServiceClass = paymentServiceTypeMap.get(paymentType);
        return applicationContext.getBean(paymentServiceClass);
    }

}
