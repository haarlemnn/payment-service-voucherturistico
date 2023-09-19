package com.voucherturistico.payment.infrastructure.http.bb;

import com.voucherturistico.payment.domain.models.request.CancelPaymentRequest;
import com.voucherturistico.payment.domain.models.request.PaymentRequest;
import com.voucherturistico.payment.domain.models.request.PaymentStatusRequest;
import com.voucherturistico.payment.infrastructure.http.bb.clients.BBPixApi;
import com.voucherturistico.payment.infrastructure.http.bb.configs.BBApiConfig;
import com.voucherturistico.payment.infrastructure.http.bb.mappers.PixTransactionMapper;
import com.voucherturistico.payment.infrastructure.http.bb.models.pix.request.CancelPixTransaction;
import com.voucherturistico.payment.infrastructure.http.bb.models.pix.request.PixTransactionRequest;
import com.voucherturistico.payment.infrastructure.http.bb.models.pix.response.PixCreatedResponse;
import com.voucherturistico.payment.infrastructure.http.bb.models.pix.response.PixDetailsResponse;
import com.voucherturistico.payment.infrastructure.http.bb.models.pix.response.PixTransactionCancelledResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BBPixService {

    private final BBPixApi bbPixApi;
    private final BBOAuthService bboAuthService;
    private final BBApiConfig bbApiConfig;

    @Autowired
    public BBPixService(BBPixApi bbPixApi, BBOAuthService bboAuthService, BBApiConfig bbApiConfig) {
        this.bbPixApi = bbPixApi;
        this.bboAuthService = bboAuthService;
        this.bbApiConfig = bbApiConfig;
    }

    public PixCreatedResponse createPixTransaction(String transactionId, PaymentRequest paymentRequest) {
        log.info("BBPixService [createPixTransaction] - Creating Pix transaction with transactionId {} and entityId {}", transactionId, bbApiConfig.getEntityId());

        PixTransactionRequest pixTransactionRequest = PixTransactionMapper.fromPaymentRequestToPixTransactionRequest(
            transactionId,
            paymentRequest,
            bbApiConfig
        );

        return this.bbPixApi.createPixTransaction(
            bbApiConfig.getApplicationKey(),
            bboAuthService.buildAuthHeader(),
            pixTransactionRequest
        );
    }

    public PixDetailsResponse getPixDetails(PaymentStatusRequest paymentStatusRequest) {
        log.info("BBPixService [getPixDetails] - Getting pix payment details with transactionId {} and entityId {}", paymentStatusRequest.getTransactionId(), bbApiConfig.getEntityId());

        return this.bbPixApi.pixDetails(
            bbApiConfig.getApplicationKey(),
            bbApiConfig.getEntityId(),
            bboAuthService.buildAuthHeader(),
            paymentStatusRequest.getTransactionId()
        );
    }

    public PixTransactionCancelledResponse cancelPixTransaction(CancelPaymentRequest cancelPaymentRequest) {
        log.info("BBPixService [cancelPixTransaction] - Cancelling pix transaction with transactionId {}", cancelPaymentRequest.getTransactionId());

        CancelPixTransaction cancelPixTransaction = new CancelPixTransaction();
        cancelPixTransaction.setNumeroConvenio(Integer.parseInt(bbApiConfig.getEntityId()));

        if (StringUtils.isNotBlank(bbApiConfig.getPixKey())) {
            cancelPixTransaction.setCodigoSolicitacaoBancoCentralBrasil(bbApiConfig.getPixKey());
        } else {
            cancelPixTransaction.setCodigoSolicitacaoBancoCentralBrasil(bbApiConfig.getPixKey());
        }

        return this.bbPixApi.cancelPixTransaction(
            bbApiConfig.getApplicationKey(),
            bboAuthService.buildAuthHeader(),
            cancelPaymentRequest.getTransactionId(),
            cancelPixTransaction
        );
    }

}
