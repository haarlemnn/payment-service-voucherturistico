package com.voucherturistico.payment.infrastructure.http.bb.configs;

import feign.okhttp.OkHttpClient;
import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.util.PemUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509TrustManager;
import java.nio.file.Paths;
import java.security.cert.CertificateException;

public class BBPixV2ApiConfig {

    @Bean
    @Profile("!production")
    public OkHttpClient developFeignClient() {
        return new OkHttpClient();
    }

    @Bean
    @Profile("production")
    public OkHttpClient feignClient() {
        final TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            }
        };

        var client = new okhttp3.OkHttpClient.Builder()
            .sslSocketFactory(getSSLFactory().getSslSocketFactory(), (X509TrustManager)trustAllCerts[0])
            .hostnameVerifier(getSSLFactory().getHostnameVerifier())
            .build();

        return new OkHttpClient(client);
    }

    private SSLFactory getSSLFactory() {
        X509ExtendedKeyManager keyManager = PemUtils
            .loadIdentityMaterial(Paths.get("cert/bb/cert-chain.pem"), Paths.get("cert/bb/key-private.pem"));

        return SSLFactory.builder()
            .withIdentityMaterial(keyManager)
            .build();
    }

}
