package com.voucherturistico.payment.infrastructure.docs;

import com.voucherturistico.payment.domain.exceptions.StandardError;
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
        responseCode = "400",
        description = "Bad request",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = StandardError.class),
            examples = @ExampleObject(
                value = "\"{\\\"message\\\":\\\"\\\",\\\"timestamp\\\":\\\"2023-08-14T23:42:26.525269084Z\\\",\\\"status\\\":400}\""
            )
        )
    )
})
public @interface DefaultAPIErrorResponse {
}
