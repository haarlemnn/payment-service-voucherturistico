package com.voucherturistico.payment.infrastructure.http.bb.configs;

import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;

public class BBOAuthApiConfig {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(BBApiConfig bbApiConfig) {
        return new BasicAuthRequestInterceptor(bbApiConfig.getClientId(), bbApiConfig.getClientSecret());
    }

    @Bean
    public Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
        return new SpringFormEncoder(new SpringEncoder(converters));
    }

}
