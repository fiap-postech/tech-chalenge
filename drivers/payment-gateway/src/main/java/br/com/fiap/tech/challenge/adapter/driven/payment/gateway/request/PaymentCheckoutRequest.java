package br.com.fiap.tech.challenge.adapter.driven.payment.gateway.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PaymentCheckoutRequest {
    private List<CheckoutItem> items;
}
