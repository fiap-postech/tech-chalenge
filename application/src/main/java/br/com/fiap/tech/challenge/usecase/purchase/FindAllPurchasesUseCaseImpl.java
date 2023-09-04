package br.com.fiap.tech.challenge.usecase.purchase;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.gateway.PurchaseReaderGateway;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class FindAllPurchasesUseCaseImpl implements FindAllPurchasesUseCase {

    private final PurchaseReaderGateway gateway;

    @Override
    public ResponseList<Purchase> list(Page page) {
        return gateway.readAll(page);
    }
}
