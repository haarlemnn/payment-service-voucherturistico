package com.voucherturistico.payment.infrastructure.http.bb.models.pix;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdditionalInformation {

    private String codigoInformacaoAdicional;
    private String textoInformacaoAdicional;

}
