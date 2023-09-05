package br.com.fiap.tech.challenge.application.gateway;

import br.com.fiap.tech.challenge.application.util.ResponseList;
import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.application.util.Page;

import java.util.UUID;

public interface PurchaseReaderGateway {
    ResponseList<Purchase> readAll(Page page);

    Purchase readById(UUID id);
}
