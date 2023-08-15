package com.voucherturistico.payment.infrastructure.http.bb.models.pixv2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PixTransaction {

    private String endToEndId;
    private String txid;

    private String valor;
    private PixTransactionValue componentesValor;

    private String chave;
    private String horario;
    private String infoPagador;

    private PixCustomer pagador;
    private List<PixRefund> devolucoes;

}
