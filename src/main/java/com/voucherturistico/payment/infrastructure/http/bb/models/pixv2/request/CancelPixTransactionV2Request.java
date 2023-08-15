package com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.request;

import com.voucherturistico.payment.infrastructure.http.bb.models.enums.PixStatus;
import lombok.Data;

@Data
public class CancelPixTransactionV2Request {

    private PixStatus status = PixStatus.REMOVIDA_PELO_USUARIO_RECEBEDOR;

}
