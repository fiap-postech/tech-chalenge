package br.com.fiap.tech.challenge.application.usecase.purchase;

import br.com.fiap.tech.challenge.application.dto.PaymentConfirmDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.enterprise.enums.PurchaseStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PaymentConfirmUseCaseImpl implements PaymentConfirmUseCase {

    private final FindPurchaseByPaymentIdUseCase findPurchaseUseCase;
    private final UpdatePurchaseStatusUseCase updatePurchaseStatusUseCase;

    @Override
    public Purchase confirm(PaymentConfirmDTO dto) {
        var purchase = findPurchaseUseCase.getPurchase(dto.getData().getId());
        return updatePurchaseStatusUseCase.update(purchase, PurchaseStatus.PAID);
    }
}
