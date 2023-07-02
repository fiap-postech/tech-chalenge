package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.domain.Purchase;

public interface StoreOrderService {
    Purchase store(Purchase purchase);
}
