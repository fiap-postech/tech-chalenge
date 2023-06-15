package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Order;
import br.com.fiap.tech.challenge.port.driven.StoreOrderService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class CreateOrderService implements br.com.fiap.tech.challenge.port.driver.CreateOrderService {

    private StoreOrderService storeOrder;

    @Override
    public Order create(Order order) {
        return null;
    }
}
