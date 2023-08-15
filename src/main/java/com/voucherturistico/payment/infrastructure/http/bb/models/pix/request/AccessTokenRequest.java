package com.voucherturistico.payment.infrastructure.http.bb.models.pix.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccessTokenRequest {

    @JsonProperty("grant_type")
    private String grantType;
    private String scope;

}
