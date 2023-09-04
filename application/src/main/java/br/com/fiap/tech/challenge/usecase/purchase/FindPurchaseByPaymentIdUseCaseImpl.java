package br.com.fiap.tech.challenge.usecase.purchase;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.gateway.PaymentGateway;
import br.com.fiap.tech.challenge.gateway.PurchaseReaderGateway;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class FindPurchaseByPaymentIdUseCaseImpl implements FindPurchaseByPaymentIdUseCase {

    private final PaymentGateway paymentGateway;
    private final PurchaseReaderGateway purchaseReaderGateway;


    @Override
    public Purchase getPurchase(String paymentId) {
        var purchaseUUID = paymentGateway.getPurchaseUUID(paymentId).orElseThrow();
        return purchaseReaderGateway.readById(UUID.fromString(purchaseUUID));
    }
}
