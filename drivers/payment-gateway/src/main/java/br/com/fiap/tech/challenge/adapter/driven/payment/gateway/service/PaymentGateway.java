package br.com.fiap.tech.challenge.adapter.driven.payment.gateway.service;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.enterprise.enums.PaymentMethod;

import java.util.Optional;

public interface PaymentGateway {

    boolean accept(PaymentMethod method);

    Optional<String> pay(Purchase purchase);

    Optional<String> getPurchaseUUID(String paymentId);
}
