package com.voucherturistico.payment.infrastructure.http.bb.clients;

import com.voucherturistico.payment.infrastructure.configs.FeignClientErrorDecoderConfig;
import com.voucherturistico.payment.infrastructure.configs.FeignClientFormEncoderConfig;
import com.voucherturistico.payment.infrastructure.http.bb.configs.BBOAuthApiConfig;
import com.voucherturistico.payment.infrastructure.http.bb.models.AccessTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Component
@FeignClient(name = "bbOAuth", url = "${bb.auth-base-url}", configuration = { BBOAuthApiConfig.class, FeignClientFormEncoderConfig.class, FeignClientErrorDecoderConfig.class })
public interface BBOAuthApi {

    @RequestMapping(method = RequestMethod.POST, value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    AccessTokenResponse authenticate(Map<String, ?> authRequest);

}
