package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.entity.Purchase;
import br.com.fiap.tech.challenge.port.driven.PurchaseReaderService;
import br.com.fiap.tech.challenge.port.driver.FindPurchaseByUUIDService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class FindPurchaseByUUIDServiceImpl implements FindPurchaseByUUIDService {

    private final PurchaseReaderService readerService;

    @Override
    public Purchase get(UUID uuid) {
        return readerService.readById(uuid);
    }
}
