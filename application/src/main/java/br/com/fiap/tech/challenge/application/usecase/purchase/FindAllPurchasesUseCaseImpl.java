package br.com.fiap.tech.challenge.application.usecase.purchase;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.application.gateway.PurchaseReaderGateway;
import br.com.fiap.tech.challenge.application.util.Page;
import br.com.fiap.tech.challenge.application.util.ResponseList;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class FindAllPurchasesUseCaseImpl implements FindAllPurchasesUseCase {

    private final PurchaseReaderGateway gateway;

    @Override
    public ResponseList<Purchase> list(Page page) {
        return gateway.readAll(page);
    }
}
