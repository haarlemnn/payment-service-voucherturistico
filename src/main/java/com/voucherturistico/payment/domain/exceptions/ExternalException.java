package com.voucherturistico.payment.domain.exceptions;

import lombok.Getter;

@Getter
public class ExternalException extends RuntimeException {

    private int statusCode;

    public ExternalException(String message) {
        super(message);
    }

    public ExternalException(String message, int statusCode) {
        super(message);
        this.setStatusCode(statusCode);
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

}
