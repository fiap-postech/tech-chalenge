package br.com.fiap.tech.challenge.adapter.driven.payment.gateway.respose;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaymentConfirmAdditionalInfo {

    @JsonProperty("authentication_code")
    private String autheticationCode;

    @JsonProperty("available_balance")
    private String availableBalance;

    @JsonProperty("ip_address")
    private String ipAddress;

    private List<PaymentConfirmItem> items;

    @JsonProperty("nsu_processadora")
    private String nsu;

    public String purchaseId() {
        return items.stream().map(i -> i.getId().split("_")[0]).findFirst().orElseThrow();
    }

}