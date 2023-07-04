package br.com.fiap.tech.challenge.adapter.driven.payment.gateway.service;

import br.com.fiap.tech.challenge.domain.PaymentMethod;
import br.com.fiap.tech.challenge.domain.entity.Payment;

public interface PaymentGateway {

    boolean accept(PaymentMethod method);

    Payment pay(Payment payment);
}
