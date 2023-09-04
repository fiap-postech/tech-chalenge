package br.com.fiap.tech.challenge.adapter.driven.payment.gateway.request;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class CheckoutItem {
    private String id;
    private String title;
    private String description;
    private String picture_url;
    private String category_id;
    private int quantity;
    private String currency_id;
    private BigDecimal unit_price;
}
