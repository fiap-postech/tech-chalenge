package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.enterprise.entity.Payment;

public interface PaymentGatewayService {
    Payment pay(Payment payment);
}
