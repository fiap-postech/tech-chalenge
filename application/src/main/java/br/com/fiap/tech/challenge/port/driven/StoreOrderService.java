package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.domain.Order;

public interface StoreOrderService {
    Order store(Order order);
}
