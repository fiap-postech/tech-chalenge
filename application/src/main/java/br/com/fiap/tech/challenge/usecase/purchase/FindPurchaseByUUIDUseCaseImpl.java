package br.com.fiap.tech.challenge.usecase.purchase;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.gateway.PurchaseReaderGateway;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class FindPurchaseByUUIDUseCaseImpl implements FindPurchaseByUUIDUseCase {

    private final PurchaseReaderGateway gateway;

    @Override
    public Purchase get(UUID uuid) {
        return gateway.readById(uuid);
    }
}
