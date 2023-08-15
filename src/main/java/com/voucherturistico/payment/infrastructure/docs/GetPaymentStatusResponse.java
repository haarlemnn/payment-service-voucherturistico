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
            mediaType = "application/json",
            schema = @Schema(implementation = PaymentStatusResponse.class),
            examples = @ExampleObject(
                value = "\"{\\\"payment\\\":{\\\"amount\\\":50.00,\\\"status\\\":\\\"SUCCESS\\\",\\\"paymentType\\\":\\\"BB\\\",\\\"paymentMethod\\\":\\\"PIX_V2\\\",\\\"transactionId\\\":\\\"MTk5MTc4ODUyNTAyMDIzLTA4LTE1VDAwOjE\\\",\\\"paymentId\\\":\\\"E0000000020230815001125223539258\\\",\\\"qrCode\\\":\\\"00020101021226870014br.gov.bcb.pix2565qrcodepix-h.bb.com.br/pix/v2/fbae6c31-dee8-4add-9c3f-b26e39747966520400005303986540550.005802BR5921PAPELARIALEITECUNHA6008BRASILIA62070503***630480FA\\\"},\\\"paymentRefund\\\":[{\\\"amount\\\":7.00,\\\"refundTransactionId\\\":\\\"MjAyMy0wOC0xNlQwMzo0NDozMC4xMTA2ODc\\\"},{\\\"amount\\\":1.00,\\\"refundTransactionId\\\":\\\"MjAyMy0wOC0xNlQwMzo0NDowNS4yODYzODM\\\"},{\\\"amount\\\":2.00,\\\"refundTransactionId\\\":\\\"RTAwMDAwMDAwMjAyMzA4MTUwMDExMjUyMjM\\\"},{\\\"amount\\\":3.00,\\\"refundTransactionId\\\":\\\"MTk5MTc4ODUyNTAyMDIzLTA4LTE1VDAwOjA\\\"},{\\\"amount\\\":37.00,\\\"refundTransactionId\\\":\\\"MTk5MTc4ODUyNTAyMDIzLTA4LTE1VDAwOjE\\\"}],\\\"status\\\":\\\"REFUNDED\\\"}\""
            )
        )
    )
})
public @interface GetPaymentStatusResponse {
}
