package com.voucherturistico.payment.infrastructure.http.bb;

import com.voucherturistico.payment.domain.models.request.CancelPaymentRequest;
import com.voucherturistico.payment.domain.models.request.PaymentRequest;
import com.voucherturistico.payment.domain.models.request.PaymentStatusRequest;
import com.voucherturistico.payment.domain.models.request.RefundPaymentRequest;
import com.voucherturistico.payment.infrastructure.http.bb.clients.BBPixV2Api;
import com.voucherturistico.payment.infrastructure.http.bb.configs.BBApiConfig;
import com.voucherturistico.payment.infrastructure.http.bb.mappers.PixTransactionMapper;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.request.CancelPixTransactionV2Request;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.request.PixRefundRequest;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.request.PixTransactionV2Request;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.response.PixDetailsV2Response;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.response.PixRefundResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BBPixV2Service {

    private final BBPixV2Api bbPixV2Api;
    private final BBOAuthService bboAuthService;
    private final BBApiConfig bbApiConfig;

    @Autowired
    public BBPixV2Service(BBPixV2Api bbPixV2Api, BBOAuthService bboAuthService, BBApiConfig bbApiConfig) {
        this.bbPixV2Api = bbPixV2Api;
        this.bboAuthService = bboAuthService;
        this.bbApiConfig = bbApiConfig;
    }

    public PixDetailsV2Response createPixTransaction(String transactionId, PaymentRequest paymentRequest) {
        log.info("BBPixV2Service [createPixTransaction] - Creating Pix transaction with transactionId {} and entityId {}", transactionId, paymentRequest.getEntityId());

        PixTransactionV2Request pixTransactionV2Request = PixTransactionMapper.fromPaymentRequestToPixTransactionV2Request(paymentRequest, bbApiConfig.getPixKey());

        return this.bbPixV2Api.createPixTransaction(
            bbApiConfig.getApplicationKey(),
            bboAuthService.buildAuthHeader(),
            transactionId,
            pixTransactionV2Request
        );
    }

    public PixDetailsV2Response getPixDetails(PaymentStatusRequest paymentStatusRequest) {
        log.info("BBPixV2Service [getPixDetails] - Getting pix payment details with transactionId {} and entityId {}", paymentStatusRequest.getTransactionId(), paymentStatusRequest.getEntityId());

        return this.bbPixV2Api.pixDetails(
            bbApiConfig.getApplicationKey(),
            bboAuthService.buildAuthHeader(),
            paymentStatusRequest.getTransactionId()
        );
    }

    public PixDetailsV2Response cancelPixTransaction(CancelPaymentRequest cancelPaymentRequest) {
        log.info("BBPixV2Service [cancelPixTransaction] - Cancelling pix transaction with transactionId {}", cancelPaymentRequest.getTransactionId());

        return this.bbPixV2Api.cancelPixTransaction(
            bbApiConfig.getApplicationKey(),
            bboAuthService.buildAuthHeader(),
            cancelPaymentRequest.getTransactionId(),
            new CancelPixTransactionV2Request()
        );
    }

    public PixRefundResponse refundPix(String transactionId, RefundPaymentRequest refundPaymentRequest) {
        log.info("BBPixV2Servce [refundPix] - Refund pix transaction with transactionId {} and paymentId {}", transactionId, refundPaymentRequest.getPaymentId());

        PixRefundRequest pixRefundRequest = new PixRefundRequest();
        pixRefundRequest.setValor(refundPaymentRequest.getAmount().toString());

        return this.bbPixV2Api.refundPix(
            bbApiConfig.getApplicationKey(),
            bboAuthService.buildAuthHeader(),
            refundPaymentRequest.getPaymentId(),
            transactionId,
            pixRefundRequest
        );
    }

}
