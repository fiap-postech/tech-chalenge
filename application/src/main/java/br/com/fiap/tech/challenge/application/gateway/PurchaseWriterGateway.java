package br.com.fiap.tech.challenge.application.gateway;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;

public interface PurchaseWriterGateway {
    Purchase write(Purchase purchase);
}
