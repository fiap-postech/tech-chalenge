package br.com.fiap.tech.challenge.application.usecase.purchase;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;

import java.util.UUID;

public interface CheckoutUseCase {
    Purchase checkout(UUID cartId);
}
