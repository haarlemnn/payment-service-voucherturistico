package com.voucherturistico.payment.infrastructure.utils;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

@Component
public class TransactionIdGenerator {

    public String generate(String key) {
        Instant timestamp = Instant.now();
        String transactionId = String.format("%s%s", timestamp, key);

        byte[] encodedBytes = Base64.getUrlEncoder().withoutPadding().encode(transactionId.getBytes(StandardCharsets.UTF_8));
        String base64Encoded = new String(encodedBytes, StandardCharsets.UTF_8);

        return base64Encoded.substring(0, Math.min(35, base64Encoded.length()));
    }

}
