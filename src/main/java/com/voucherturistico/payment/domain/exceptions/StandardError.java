package com.voucherturistico.payment.domain.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class StandardError {

    private String message;
    private Instant timestamp;
    private int status;

}
