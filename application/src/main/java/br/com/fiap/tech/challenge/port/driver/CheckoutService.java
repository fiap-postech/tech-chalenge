package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.domain.Order;

import java.util.UUID;

public interface CheckoutService {
    Order checkout(UUID cartId);
}
