package br.com.fiap.tech.challenge.usecase.purchase;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.enterprise.enums.PurchaseStatus;
import br.com.fiap.tech.challenge.gateway.PurchaseWriterGateway;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class UpdatePurchaseStatusUseCaseImpl implements UpdatePurchaseStatusUseCase {

    private final FindPurchaseByUUIDUseCase findPurchaseUseCase;
    private final PurchaseWriterGateway gateway;

    @Override
    public Purchase update(UUID uuid, PurchaseStatus status) {
        return update(findPurchaseUseCase.get(uuid), status);
    }

    @Override
    public Purchase update(Purchase purchase, PurchaseStatus status) {
        var updatedPurchase = switch (status) {
            case WAITING_PAID -> purchase;
            case PAID -> purchase.paid();
            case MAKING -> purchase.making();
            case MADE -> purchase.made();
            case DELIVERED -> purchase.delivered();
            case FINISHED -> purchase.finished();
        };

        return gateway.write(updatedPurchase);
    }
}
