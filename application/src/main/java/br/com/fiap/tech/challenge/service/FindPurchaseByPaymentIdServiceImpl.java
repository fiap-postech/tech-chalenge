package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.port.driven.PaymentGatewayService;
import br.com.fiap.tech.challenge.port.driven.PurchaseReaderService;
import br.com.fiap.tech.challenge.port.driver.FindPurchaseByPaymentIdService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class FindPurchaseByPaymentIdServiceImpl implements FindPurchaseByPaymentIdService {

    private final PaymentGatewayService paymentGateway;
    private final PurchaseReaderService readerService;


    @Override
    public Purchase getPurchase(String paymentId) {
        var purchaseUUID = paymentGateway.getPurchaseUUID(paymentId).orElseThrow();
        return readerService.readById(UUID.fromString(purchaseUUID));
    }
}
