package br.com.fiap.tech.challenge.application.usecase.purchase;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;

public interface FindPurchaseByPaymentIdUseCase {
    Purchase getPurchase(String paymentId);
}
