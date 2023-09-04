package br.com.fiap.tech.challenge.adapter.driven.payment.gateway.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentCheckoutResponse {
    private String sandbox_init_point;
}
