package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.enterprise.enums.PurchaseStatus;
import br.com.fiap.tech.challenge.port.driven.PurchaseWriterService;
import br.com.fiap.tech.challenge.port.driver.UpdatePurchaseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdatePurchaseServiceImpl implements UpdatePurchaseService {

    private final PurchaseWriterService purchaseWriterService;

    @Override
    public Purchase updateStatus(Purchase purchase, PurchaseStatus status) {
        var purchaseByUpdate = switch (status) {
            case PAID -> purchase;
            case MAKING -> purchase.making();
            case MADE -> purchase.made();
            case DELIVERED -> purchase.delivered();
            case FINISHED -> purchase.finished();
        };

        return purchaseWriterService.write(purchaseByUpdate);
    }
}
