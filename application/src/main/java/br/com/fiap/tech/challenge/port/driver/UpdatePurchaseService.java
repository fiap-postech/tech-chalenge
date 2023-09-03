package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.enterprise.enums.PurchaseStatus;

public interface UpdatePurchaseService {

    Purchase updateStatus(Purchase purchase, PurchaseStatus status);
}
