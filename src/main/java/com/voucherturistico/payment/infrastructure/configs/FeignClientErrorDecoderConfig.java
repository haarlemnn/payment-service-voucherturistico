package com.voucherturistico.payment.infrastructure.configs;

import com.voucherturistico.payment.domain.exceptions.ExternalException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

@Slf4j
public class FeignClientErrorDecoderConfig implements ErrorDecoder {

    @SneakyThrows
    @Override
    public Exception decode(String s, Response response) {
        Response.Body responseBody = response.body();

        if (responseBody != null && responseBody.length() > 0) {
            String body = IOUtils.toString(response.body().asInputStream(), String.valueOf(Charsets.UTF_8));
            log.error("BBFeignClientConfig [decode] - Received an error from Banco do Brasil\n: {}", body);

            return new ExternalException(body, response.status());
        }

        log.error("BBFeignClientConfig [decode] - Received an error from Banco do Brasil without body");
        return new ExternalException("Internal Server Error", 500);
    }

}
