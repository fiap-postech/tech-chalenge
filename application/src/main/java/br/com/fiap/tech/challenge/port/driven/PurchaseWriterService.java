package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;

public interface PurchaseWriterService {
    Purchase write(Purchase purchase);
}
