package br.com.fiap.tech.challenge.adapter.driven.payment.gateway.respose;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentConfirmResponse {

    @JsonProperty("additional_info")
    private PaymentConfirmAdditionalInfo additionalInfo;
}
