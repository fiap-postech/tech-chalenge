package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Purchase;
import br.com.fiap.tech.challenge.port.driven.StoreOrderService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class CreateOrderService implements br.com.fiap.tech.challenge.port.driver.CreateOrderService {

    private StoreOrderService storeOrder;

    @Override
    public Purchase create(Purchase purchase) {
        return null;
    }
}
