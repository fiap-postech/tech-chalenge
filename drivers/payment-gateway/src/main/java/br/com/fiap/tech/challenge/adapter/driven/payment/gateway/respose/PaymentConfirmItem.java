package br.com.fiap.tech.challenge.adapter.driven.payment.gateway.respose;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentConfirmItem {

    @JsonProperty("category_id")
    private String category;

    private String description;

    private String id;

    @JsonProperty("picture_url")
    private String imageUrl;

    private String quantity;

    private String title;

    @JsonProperty("unit_price")
    private String price;

    public String itemId() {
        return id.split("_")[1];
    }
}





