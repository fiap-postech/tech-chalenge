package br.com.fiap.tech.challenge.usecase.purchase;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.enterprise.enums.PurchaseStatus;
import br.com.fiap.tech.challenge.gateway.PurchaseWriterGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class UpdatePurchaseUseCaseImpl implements UpdatePurchaseUseCase {

    private final PurchaseWriterGateway gateway;

    @Override
    public Purchase updateStatus(Purchase purchase, PurchaseStatus status) {
        var purchaseByUpdate = switch (status) {
            case WAITING_PAID -> purchase;
            case PAID -> purchase.paid();
            case MAKING -> purchase.making();
            case MADE -> purchase.made();
            case DELIVERED -> purchase.delivered();
            case FINISHED -> purchase.finished();
        };

        return gateway.write(purchaseByUpdate);
    }
}
