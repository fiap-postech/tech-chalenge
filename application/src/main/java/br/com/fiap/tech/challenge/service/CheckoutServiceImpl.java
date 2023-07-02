package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Purchase;
import br.com.fiap.tech.challenge.port.driver.CheckoutService;
import br.com.fiap.tech.challenge.port.driver.CreatePurchaseService;
import br.com.fiap.tech.challenge.port.driver.FindCartByUUIDService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class CheckoutServiceImpl implements CheckoutService {

    private final FindCartByUUIDService cartFinder;
    private final CreatePurchaseService purchaseService;

    @Override
    public Purchase checkout(UUID cartId) {
        var cart = cartFinder.get(cartId);

        //TODO payment gateway will be place here

        return null;
    }
}
