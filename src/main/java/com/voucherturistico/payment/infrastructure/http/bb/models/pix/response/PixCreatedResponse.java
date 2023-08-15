package com.voucherturistico.payment.infrastructure.http.bb.models.pix.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voucherturistico.payment.infrastructure.http.bb.models.enums.PixStatus;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PixCreatedResponse {

    private String timestampCriacaoSolicitacao;

    private String codigoConciliacaoSolicitante;
    private String numeroVersaoSolicitacaoPagamento;

    private String linkQrCode;
    private String qrCode;

    private PixStatus estadoSolicitacao;

}
