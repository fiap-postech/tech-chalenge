package br.com.fiap.tech.challenge.usecase.purchase;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.enterprise.enums.PurchaseStatus;

public interface UpdatePurchaseUseCase {

    Purchase updateStatus(Purchase purchase, PurchaseStatus status);
}
