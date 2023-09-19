package com.voucherturistico.payment.infrastructure.http.bb.clients;

import com.voucherturistico.payment.infrastructure.configs.FeignClientDecoderConfig;
import com.voucherturistico.payment.infrastructure.configs.FeignClientErrorDecoderConfig;
import com.voucherturistico.payment.infrastructure.http.bb.models.pix.request.CancelPixTransaction;
import com.voucherturistico.payment.infrastructure.http.bb.models.pix.request.PixTransactionRequest;
import com.voucherturistico.payment.infrastructure.http.bb.models.pix.response.PixCreatedResponse;
import com.voucherturistico.payment.infrastructure.http.bb.models.pix.response.PixDetailsResponse;
import com.voucherturistico.payment.infrastructure.http.bb.models.pix.response.PixTransactionCancelledResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "bb-pix", url = "${bb.base-url}/pix-bb/v1", configuration = { FeignClientErrorDecoderConfig.class, FeignClientDecoderConfig.class })
public interface BBPixApi {

    @RequestMapping(method = RequestMethod.POST, value = "/arrecadacao-qrcodes")
    PixCreatedResponse createPixTransaction(
        @RequestParam(name = "gw-app-key") String appKey,
        @RequestHeader(name = "Authorization") String accessToken,
        @RequestBody PixTransactionRequest pixTransactionRequest
    );

    @RequestMapping(method = RequestMethod.GET, value = "/arrecadacao-qrcodes/{transactionId}")
    PixDetailsResponse pixDetails(
        @RequestParam(name = "gw-app-key") String appKey,
        @RequestParam(name = "numeroConvenio") String number,
        @RequestHeader(name = "Authorization") String accessToken,
        @PathVariable(name = "transactionId") String transactionId
    );

    @RequestMapping(method = RequestMethod.POST, value = "/arrecadacao-qrcodes/{transactionId}/baixar")
    PixTransactionCancelledResponse cancelPixTransaction(
        @RequestParam(name = "gw-app-key") String appKey,
        @RequestHeader(name = "Authorization") String accessToken,
        @PathVariable(name = "transactionId") String transactionId,
        @RequestBody CancelPixTransaction cancelPixTransaction
    );

}
