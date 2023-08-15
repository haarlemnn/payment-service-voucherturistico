package com.voucherturistico.payment.infrastructure.http.bb;

import com.voucherturistico.payment.infrastructure.http.bb.clients.BBOAuthApi;
import com.voucherturistico.payment.infrastructure.http.bb.configs.BBApiConfig;
import com.voucherturistico.payment.infrastructure.http.bb.models.AccessTokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class BBOAuthService {

    private final BBOAuthApi bboAuthApi;

    private final BBApiConfig bbApiConfig;

    @Autowired
    public BBOAuthService(BBOAuthApi bboAuthApi, BBApiConfig bbApiConfig) {
        this.bboAuthApi = bboAuthApi;
        this.bbApiConfig = bbApiConfig;
    }

    private String doAuth() {
        Map<String, Object> authRequest = new HashMap<>();
        authRequest.put("grant_type", bbApiConfig.getGrantType());
        authRequest.put("scope", bbApiConfig.getScope());

        AccessTokenResponse accessTokenResponse = bboAuthApi.authenticate(authRequest);
        return accessTokenResponse.getAccessToken();
    }

    public String buildAuthHeader() {
        log.info("BBOAuthService [buildAuthHeader] - Authenticating in Banco do Brasil API");
        String accessToken = this.doAuth();
        return String.format("Bearer %s", accessToken);
    }

}
