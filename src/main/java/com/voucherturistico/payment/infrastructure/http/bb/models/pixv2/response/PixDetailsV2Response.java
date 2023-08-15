package com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.response;

import com.voucherturistico.payment.infrastructure.http.bb.models.enums.PixStatus;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.AdditionalInformation;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.PixCalendar;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.PixCustomer;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.PixTransaction;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.PixValue;
import lombok.Data;

import java.util.List;

@Data
public class PixDetailsV2Response {

    private PixCalendar calendario;
    private PixCustomer devedor;
    private PixValue valor;

    private String chave;
    private String solicitacaoPagador;

    private String txid;
    private PixStatus status;
    private String pixCopiaECola;
    private String location;

    private int revisao;

    private List<AdditionalInformation> infoAdicionais;
    private List<PixTransaction> pix;

}
