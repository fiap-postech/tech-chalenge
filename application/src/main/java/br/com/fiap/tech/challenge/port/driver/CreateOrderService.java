package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.domain.Order;

public interface CreateOrderService {
    Order create(Order order);
}
