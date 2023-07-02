package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.domain.Purchase;

public interface CreatePurchaseService {
    Purchase create(Purchase purchase);
}
