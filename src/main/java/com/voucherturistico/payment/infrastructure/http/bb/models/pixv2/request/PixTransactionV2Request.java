package com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.AdditionalInformation;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.PixCalendar;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.PixCustomer;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.PixValue;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PixTransactionV2Request {

    private PixCalendar calendario;
    private PixCustomer devedor;
    private PixValue valor;

    private String chave;
    private String solicitacaoPagador;

    private List<AdditionalInformation> infoAdicionais;

}
