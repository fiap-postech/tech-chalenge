package br.com.fiap.tech.challenge.adapter.controller.purchase;

import br.com.fiap.tech.challenge.adapter.dto.PurchaseDTO;

public interface FindPurchaseByUUIDController {
    PurchaseDTO get(String uuid);
}
