package br.com.fiap.tech.challenge.gateway;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;

import java.util.UUID;

public interface PurchaseReaderGateway {
    ResponseList<Purchase> readAll(Page page);

    Purchase readById(UUID id);
}
