package com.voucherturistico.payment.infrastructure.http.bb.models.pixv2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PixTransactionValueData {

    private String valor;

}
