package com.voucherturistico.payment.infrastructure.http.bb.models.pix.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voucherturistico.payment.infrastructure.http.bb.models.enums.PixStatus;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PixTransactionCancelledResponse {

    private String id;
    private PixStatus estadoSolicitacao;

}
