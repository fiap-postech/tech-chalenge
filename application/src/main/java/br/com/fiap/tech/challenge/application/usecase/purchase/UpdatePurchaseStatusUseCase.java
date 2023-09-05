package br.com.fiap.tech.challenge.application.usecase.purchase;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.enterprise.enums.PurchaseStatus;

import java.util.UUID;

public interface UpdatePurchaseStatusUseCase {

    Purchase update(UUID purchaseUUID, PurchaseStatus status);
    Purchase update(Purchase purchase, PurchaseStatus status);
}
