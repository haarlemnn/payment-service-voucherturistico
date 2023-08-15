package com.voucherturistico.payment.infrastructure.http.bb.configs;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class BBApiConfig {

    @Value("${bb.application-key}")
    public String applicationKey;

    @Value("${bb.client-id}")
    public String clientId;
    @Value("${bb.client-secret}")
    public String clientSecret;

    @Value("${bb.grant-type}")
    public String grantType;
    @Value("${bb.scope}")
    public String scope;

    @Value("${bb.pix-key}")
    public String pixKey;

}
