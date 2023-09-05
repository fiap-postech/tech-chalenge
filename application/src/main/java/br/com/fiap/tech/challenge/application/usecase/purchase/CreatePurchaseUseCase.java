package br.com.fiap.tech.challenge.application.usecase.purchase;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;

public interface CreatePurchaseUseCase {
    Purchase create(Purchase purchase);
}
