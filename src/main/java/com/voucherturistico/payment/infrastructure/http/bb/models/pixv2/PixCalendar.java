package com.voucherturistico.payment.infrastructure.http.bb.models.pixv2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PixCalendar {

    private String criacao;
    private int expiracao;

}
