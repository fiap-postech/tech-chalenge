package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Purchase;
import br.com.fiap.tech.challenge.port.driven.PurchaseReaderService;
import br.com.fiap.tech.challenge.port.driver.FindAllPurchasesService;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class FindAllPurchasesServiceImpl implements FindAllPurchasesService {

    private final PurchaseReaderService readerService;

    @Override
    public ResponseList<Purchase> list(Page page) {
        return readerService.readAll(page);
    }
}
