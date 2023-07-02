package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.domain.Purchase;

public interface CreateOrderService {
    Purchase create(Purchase purchase);
}
