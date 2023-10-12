package com.voucherturistico.payment.infrastructure.http.bb.clients;

import com.voucherturistico.payment.infrastructure.configs.FeignClientDecoderConfig;
import com.voucherturistico.payment.infrastructure.configs.FeignClientErrorDecoderConfig;
import com.voucherturistico.payment.infrastructure.http.bb.configs.BBPixV2ApiConfig;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.request.CancelPixTransactionV2Request;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.request.PixRefundRequest;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.request.PixTransactionV2Request;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.response.PixDetailsV2Response;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.response.PixRefundResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "bb-pix-v2", url = "${bb.base-url-v2}/pix/v2", configuration = { BBPixV2ApiConfig.class, FeignClientErrorDecoderConfig.class, FeignClientDecoderConfig.class })
public interface BBPixV2Api {

    @RequestMapping(method = RequestMethod.PUT, value = "/cob/{transactionId}")
    PixDetailsV2Response createPixTransaction(
        @RequestParam(name = "gw-app-key") String appKey,
        @RequestHeader(name = "Authorization") String accessToken,
        @PathVariable(name = "transactionId") String transactionId,
        @RequestBody PixTransactionV2Request pixTransactionV2Request
    );

    @RequestMapping(method = RequestMethod.GET, value = "/cob/{transactionId}")
    PixDetailsV2Response pixDetails(
        @RequestParam(name = "gw-app-key") String appKey,
        @RequestHeader(name = "Authorization") String accessToken,
        @PathVariable(name = "transactionId") String transactionId
    );

    @RequestMapping(method = RequestMethod.PATCH, value = "/cob/{transactionId}")
    PixDetailsV2Response cancelPixTransaction(
        @RequestParam(name = "gw-app-key") String appKey,
        @RequestHeader(name = "Authorization") String accessToken,
        @PathVariable(name = "transactionId") String transactionId,
        @RequestBody CancelPixTransactionV2Request cancelPixTransactionV2Request
    );

    @RequestMapping(method = RequestMethod.PUT, value = "/pix/{paymentId}/devolucao/{transactionId}")
    PixRefundResponse refundPix(
        @RequestParam(name = "gw-app-key") String appKey,
        @RequestHeader(name = "Authorization") String accessToken,
        @PathVariable(name = "paymentId") String paymentId,
        @PathVariable(name = "transactionId") String transactionId,
        @RequestBody PixRefundRequest pixRefundRequest
    );

}
