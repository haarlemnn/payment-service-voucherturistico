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
                value = "\"{\\\"payment\\\":{\\\"amount\\\":1.00,\\\"status\\\":\\\"PROCESSING\\\",\\\"paymentType\\\":\\\"BB\\\",\\\"paymentMethod\\\":\\\"PIX_V2\\\",\\\"transactionId\\\":\\\"MjAyMy0wOC0xNlQwMzo0NDowNS4yODYzODM\\\"}}\""
            )
        )
    ),
})
public @interface RefundPaymentResponse {
}
