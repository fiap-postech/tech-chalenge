package br.com.fiap.tech.challenge.usecase.purchase;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.gateway.PurchaseWriterGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CreatePurchaseUseCaseImpl implements CreatePurchaseUseCase {

    private final PurchaseWriterGateway writer;

    @Override
    public Purchase create(Purchase purchase) {
        return writer.write(purchase);
    }
}
