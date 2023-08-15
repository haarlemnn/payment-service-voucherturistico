package com.voucherturistico.payment.infrastructure.http.bb.models.pix.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voucherturistico.payment.infrastructure.http.bb.models.enums.PixStatus;
import com.voucherturistico.payment.infrastructure.http.bb.models.pix.AdditionalInformation;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PixDetailsResponse {

    private String codigoConciliacaoSolicitante;
    private String codigoGuiaRecebimento;

    private String emailDevedor;
    private Integer codigoPaisTelefoneDevedor;
    private Integer dddTelefoneDevedor;
    private Integer numeroTelefoneDevedor;
    private Long cpfDevedor;
    private Long cnpjDevedor;
    private String nomeDevedor;

    private String timestampCriacaoSolicitacao;
    private Integer quantidadeSegundoExpiracao;
    private PixStatus estadoSolicitacao;
    private Integer numeroVersaoSolicitacaoPagamento;

    private String linkQrCode;
    private String qrCode;

    private BigDecimal valorOriginalSolicitacao;
    private String codigoSolicitacaoBancoCentralBrasil;
    private String descricaoSolicitacaoPagamento;

    private String codigoIdentificadorPagamento;
    private BigDecimal valorPagamento;
    private String timestampPagamento;

    private Long cpfPagador;
    private Long cnpjPagador;
    private String nomeClientePagador;
    private String textoInformativoPagador;

    private List<AdditionalInformation> listaInformacaoAdicional;

}
