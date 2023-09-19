package com.voucherturistico.payment.infrastructure.http.bb;

import com.voucherturistico.payment.domain.enums.PaymentMethodType;
import com.voucherturistico.payment.domain.enums.PaymentType;
import com.voucherturistico.payment.domain.models.request.CancelPaymentRequest;
import com.voucherturistico.payment.domain.models.request.PaymentRequest;
import com.voucherturistico.payment.domain.models.request.PaymentStatusRequest;
import com.voucherturistico.payment.domain.models.request.RefundPaymentRequest;
import com.voucherturistico.payment.domain.models.response.PaymentStatusResponse;
import com.voucherturistico.payment.domain.services.PaymentService;
import com.voucherturistico.payment.infrastructure.http.bb.configs.BBApiConfig;
import com.voucherturistico.payment.infrastructure.http.bb.mappers.PixDetailsMapper;
import com.voucherturistico.payment.infrastructure.http.bb.models.pix.response.PixCreatedResponse;
import com.voucherturistico.payment.infrastructure.http.bb.models.pix.response.PixDetailsResponse;
import com.voucherturistico.payment.infrastructure.http.bb.models.pix.response.PixTransactionCancelledResponse;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.response.PixDetailsV2Response;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.response.PixRefundResponse;
import com.voucherturistico.payment.infrastructure.utils.TransactionIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BBPaymentService implements PaymentService {

    private final BBPixService bbPixService;
    private final BBPixV2Service bbPixV2Service;
    private final BBApiConfig bbApiConfig;

    private final TransactionIdGenerator transactionIdGenerator;

    @Autowired
    public BBPaymentService(BBPixService bbPixService, BBPixV2Service bbPixV2Service, BBApiConfig bbApiConfig, TransactionIdGenerator transactionIdGenerator) {
        this.bbPixService = bbPixService;
        this.bbPixV2Service = bbPixV2Service;
        this.bbApiConfig = bbApiConfig;
        this.transactionIdGenerator = transactionIdGenerator;
    }

    @Override
    public PaymentStatusResponse initPayment(PaymentRequest paymentRequest) {
        this.setProviderSpecifics(paymentRequest.getProviderSpecifics());

        String transactionId = transactionIdGenerator.generate(paymentRequest.getCustomerDocument());

        if (paymentRequest.getPaymentMethod() == PaymentMethodType.PIX) {
            PixCreatedResponse pixCreatedResponse = this.bbPixService.createPixTransaction(transactionId, paymentRequest);
            return PixDetailsMapper.fromPixCreatedToPaymentStatusResponse(pixCreatedResponse, paymentRequest.getAmount());
        }
        PixDetailsV2Response pixDetailsResponse = this.bbPixV2Service.createPixTransaction(transactionId, paymentRequest);
        return PixDetailsMapper.fromPixV2ToPaymentStatusResponse(pixDetailsResponse);
    }

    @Override
    public PaymentStatusResponse readPayment(PaymentStatusRequest paymentStatusRequest) {
        this.setProviderSpecifics(paymentStatusRequest.getProviderSpecifics());

        if (paymentStatusRequest.getPaymentMethod() == PaymentMethodType.PIX) {
            PixDetailsResponse pixDetailsResponse = this.bbPixService.getPixDetails(paymentStatusRequest);
            return PixDetailsMapper.fromPixToPaymentStatusResponse(paymentStatusRequest.getTransactionId(), pixDetailsResponse);
        }

        PixDetailsV2Response pixDetailsResponse = this.bbPixV2Service.getPixDetails(paymentStatusRequest);
        return PixDetailsMapper.fromPixV2ToPaymentStatusResponse(pixDetailsResponse);
    }

    @Override
    public PaymentStatusResponse cancelPayment(CancelPaymentRequest cancelPaymentRequest) {
        this.setProviderSpecifics(cancelPaymentRequest.getProviderSpecifics());

        if (cancelPaymentRequest.getPaymentMethod() == PaymentMethodType.PIX) {
            PixTransactionCancelledResponse pixDetailsResponse = this.bbPixService.cancelPixTransaction(cancelPaymentRequest);
            return PixDetailsMapper.fromPixCancelledToPaymentStatusResponse(cancelPaymentRequest.getTransactionId(), pixDetailsResponse);
        }

        PixDetailsV2Response pixDetailsResponse = this.bbPixV2Service.cancelPixTransaction(cancelPaymentRequest);
        return PixDetailsMapper.fromPixV2ToPaymentStatusResponse(pixDetailsResponse);
    }

    /**
     * This is supported only by BBPixV2Service
     */
    @Override
    public PaymentStatusResponse refundPayment(RefundPaymentRequest refundPaymentRequest) {
        this.setProviderSpecifics(refundPaymentRequest.getProviderSpecifics());

        String transactionId = transactionIdGenerator.generate(refundPaymentRequest.getPaymentId());

        PixRefundResponse pixDetailsResponse = this.bbPixV2Service.refundPix(transactionId, refundPaymentRequest);
        return PixDetailsMapper.fromPixRefundToPaymentStatusResponse(pixDetailsResponse);
    }

    @Override
    public PaymentType type() {
        return PaymentType.BB;
    }

    @Override
    public void setProviderSpecifics(Map<String, String> providerSpecifics) {
        bbApiConfig.setApplicationKey(providerSpecifics.get("applicationKey"));
        bbApiConfig.setClientId(providerSpecifics.get("clientId"));
        bbApiConfig.setClientSecret(providerSpecifics.get("clientSecret"));
        bbApiConfig.setGrantType(providerSpecifics.get("grantType"));
        bbApiConfig.setScope(providerSpecifics.get("scope"));
        bbApiConfig.setEntityId(providerSpecifics.get("entityId"));
        bbApiConfig.setPixKey(providerSpecifics.get("pixKey"));
    }

}
