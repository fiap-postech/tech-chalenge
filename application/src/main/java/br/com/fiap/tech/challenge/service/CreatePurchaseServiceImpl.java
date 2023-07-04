package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Purchase;
import br.com.fiap.tech.challenge.port.driven.PurchaseWriterService;
import br.com.fiap.tech.challenge.port.driver.CreatePurchaseService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CreatePurchaseServiceImpl implements CreatePurchaseService {

    private final PurchaseWriterService writer;

    @Override
    public Purchase create(Purchase purchase) {
        return writer.write(purchase);
    }
}
