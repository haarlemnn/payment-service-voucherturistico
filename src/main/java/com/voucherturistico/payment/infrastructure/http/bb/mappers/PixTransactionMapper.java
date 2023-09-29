package com.voucherturistico.payment.infrastructure.http.bb.mappers;

import com.voucherturistico.payment.domain.models.request.PaymentRequest;
import com.voucherturistico.payment.infrastructure.http.bb.configs.BBApiConfig;
import com.voucherturistico.payment.infrastructure.http.bb.models.pix.request.PixTransactionRequest;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.PixCalendar;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.PixCustomer;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.PixValue;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.request.PixTransactionV2Request;
import org.apache.commons.lang3.StringUtils;

public class PixTransactionMapper {

    public static PixTransactionRequest fromPaymentRequestToPixTransactionRequest(String transactionId, PaymentRequest paymentRequest, BBApiConfig bbApiConfig) {
        PixTransactionRequest pixTransactionRequest = new PixTransactionRequest();
        pixTransactionRequest.setNumeroConvenio(Integer.parseInt(bbApiConfig.getEntityId()));

        if (StringUtils.isNotBlank(paymentRequest.getBarcode())) {
            pixTransactionRequest.setIndicadorCodigoBarras("S");
            pixTransactionRequest.setCodigoGuiaRecebimento(paymentRequest.getBarcode());
        } else {
            pixTransactionRequest.setIndicadorCodigoBarras("N");
            pixTransactionRequest.setCodigoGuiaRecebimento(transactionId);
        }

        // Pix
        pixTransactionRequest.setCodigoSolicitacaoBancoCentralBrasil(bbApiConfig.getPixKey());

        // Transaction amount
        pixTransactionRequest.setValorOriginalSolicitacao(paymentRequest.getAmount());

        // Customer details
        pixTransactionRequest.setCpfDevedor(paymentRequest.getCustomerDocument());
        pixTransactionRequest.setNomeDevedor(paymentRequest.getCustomerName());
        pixTransactionRequest.setEmailDevedor(paymentRequest.getCustomerEmail());

        if (StringUtils.isNotBlank(paymentRequest.getCustomerPhone())) {
            String phoneCode = paymentRequest.getCustomerPhone().substring(0, 2);
            String phone = paymentRequest.getCustomerPhone().substring(2);

            pixTransactionRequest.setDddTelefoneDevedor(Integer.valueOf(phoneCode));
            pixTransactionRequest.setNumeroTelefoneDevedor(phone);
        }

        pixTransactionRequest.setQuantidadeSegundoExpiracao(3600);

        return pixTransactionRequest;
    }

    public static PixTransactionV2Request fromPaymentRequestToPixTransactionV2Request(PaymentRequest paymentRequest, BBApiConfig bbApiConfig) {
        PixTransactionV2Request pixTransactionV2Request = new PixTransactionV2Request();

        PixCalendar pixCalendar = new PixCalendar();
        pixCalendar.setExpiracao(3600);

        PixCustomer pixCustomer = new PixCustomer();
        pixCustomer.setNome(paymentRequest.getCustomerName());
        pixCustomer.setCpf(paymentRequest.getCustomerDocument());

        PixValue pixValue = new PixValue();
        pixValue.setOriginal(paymentRequest.getAmount().toString());

        // Pix
        pixTransactionV2Request.setCalendario(pixCalendar);
        pixTransactionV2Request.setDevedor(pixCustomer);
        pixTransactionV2Request.setValor(pixValue);
        pixTransactionV2Request.setChave(bbApiConfig.getPixKey());

        return pixTransactionV2Request;
    }

}
