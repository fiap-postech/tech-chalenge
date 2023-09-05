package br.com.fiap.tech.challenge.adapter.controller.purchase;

import br.com.fiap.tech.challenge.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.enterprise.enums.PurchaseStatus;

public interface UpdatePurchaseStatusController {

    PurchaseDTO update(String purchaseUUID, PurchaseStatus status);
}
