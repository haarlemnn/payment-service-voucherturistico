package com.voucherturistico.payment.infrastructure.http.bb.models.pix.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.voucherturistico.payment.infrastructure.http.bb.models.pix.AdditionalInformation;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PixTransactionRequest {

    private int numeroConvenio;

    private String indicadorCodigoBarras;
    private String codigoGuiaRecebimento;

    private String emailDevedor;
    private Integer codigoPaisTelefoneDevedor;
    private Integer dddTelefoneDevedor;
    private String numeroTelefoneDevedor;
    private String cpfDevedor;
    private String nomeDevedor;

    private String codigoSolicitacaoBancoCentralBrasil;
    private String descricaoSolicitacaoPagamento;
    private BigDecimal valorOriginalSolicitacao;
    private Integer quantidadeSegundoExpiracao;

    private List<AdditionalInformation> listaInformacaoAdicional;

}
