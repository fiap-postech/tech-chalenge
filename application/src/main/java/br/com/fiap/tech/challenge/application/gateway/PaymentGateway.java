package br.com.fiap.tech.challenge.application.gateway;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;

import java.util.Optional;

public interface PaymentGateway {
    Optional<String> pay(Purchase purchase);

    Optional<String> getPurchaseUUID(String paymentId);
}
