package com.voucherturistico.payment.infrastructure.http.bb.models.pix.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CancelPixTransaction {

    private int numeroConvenio;
    private String codigoSolicitacaoBancoCentralBrasil;
    private Integer cpfDevedor;

}
