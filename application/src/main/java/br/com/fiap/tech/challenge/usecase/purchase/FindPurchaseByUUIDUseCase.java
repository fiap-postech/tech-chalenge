package br.com.fiap.tech.challenge.usecase.purchase;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;

import java.util.UUID;

public interface FindPurchaseByUUIDUseCase {

    Purchase get(UUID uuid);

}
