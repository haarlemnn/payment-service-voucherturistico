package com.voucherturistico.payment.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PixPayment extends Payment {

    private String qrCode;
    private String qrCodeUrl;

}
