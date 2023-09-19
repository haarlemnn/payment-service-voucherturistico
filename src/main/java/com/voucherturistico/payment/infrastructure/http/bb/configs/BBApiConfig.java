package com.voucherturistico.payment.infrastructure.http.bb.configs;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class BBApiConfig {

    public String applicationKey;
    public String clientId;
    public String clientSecret;

    public String grantType;
    public String scope;

    private String entityId;
    public String pixKey;

}
