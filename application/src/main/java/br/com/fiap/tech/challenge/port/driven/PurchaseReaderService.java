package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.domain.Purchase;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;

import java.util.UUID;

public interface PurchaseReaderService {
    ResponseList<Purchase> readAll(Page page);

    Purchase readById(UUID id);
}
