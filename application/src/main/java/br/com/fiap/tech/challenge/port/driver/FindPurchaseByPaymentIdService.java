package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;

public interface FindPurchaseByPaymentIdService {
    Purchase getPurchase(String paymentId);
}
