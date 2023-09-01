package br.com.fiap.tech.challenge.adapter.driven.payment.gateway.service;

import br.com.fiap.tech.challenge.enterprise.enums.PaymentMethod;
import br.com.fiap.tech.challenge.enterprise.entity.Payment;

public interface PaymentGateway {

    boolean accept(PaymentMethod method);

    Payment pay(Payment payment);
}
