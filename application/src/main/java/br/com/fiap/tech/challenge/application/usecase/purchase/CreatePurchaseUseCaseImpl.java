package br.com.fiap.tech.challenge.application.usecase.purchase;

import br.com.fiap.tech.challenge.application.gateway.PurchaseWriterGateway;
import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CreatePurchaseUseCaseImpl implements CreatePurchaseUseCase {

    private final PurchaseWriterGateway writer;

    @Override
    public Purchase create(Purchase purchase) {
        return writer.write(purchase);
    }
}
