package com.voucherturistico.payment.infrastructure.docs;

import com.voucherturistico.payment.domain.models.response.PaymentStatusResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ METHOD, TYPE })
@ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Ok",
        content = @Content(
            schema = @Schema(implementation = PaymentStatusResponse.class),
            examples = @ExampleObject(
                value = "\"{\\\"payment\\\":{\\\"amount\\\":10.00,\\\"status\\\":\\\"PENDING\\\",\\\"paymentType\\\":\\\"BB\\\",\\\"paymentMethod\\\":\\\"PIX_V2\\\",\\\"transactionId\\\":\\\"MTk5MTc4ODUyNTAyMDIzLTA4LTE2VDAyOjU\\\",\\\"qrCode\\\":\\\"00020101021226870014br.gov.bcb.pix2565qrcodepix-h.bb.com.br/pix/v2/67d2618b-75a4-47cb-86d2-00fb7cc0180d520400005303986540510.005802BR5921PAPELARIALEITECUNHA6008BRASILIA62070503***6304C8C1\\\",\\\"qrCodeUrl\\\":\\\"qrcodepix-h.bb.com.br/pix/v2/67d2618b-75a4-47cb-86d2-00fb7cc0180d\\\"}}\""
            )
        )
    ),
})
public @interface InitPaymentResponse {
}
