package com.voucherturistico.payment.infrastructure.http.bb.models.pixv2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voucherturistico.payment.infrastructure.http.bb.models.enums.PixStatus;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PixRefund {

    private String id;
    private String rtrId;
    private String valor;
    private String natureza;
    private String descricao;
    private PixHour horario;
    private PixStatus status;
    private String motivo;

}
