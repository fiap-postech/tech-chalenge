package br.com.fiap.tech.challenge.usecase.purchase;

import br.com.fiap.tech.challenge.dto.PaymentConfirmDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.enterprise.enums.PurchaseStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PaymentConfirmUseCaseImpl implements PaymentConfirmUseCase {

    private final FindPurchaseByPaymentIdUseCase findPurchaseUseCase;
    private final UpdatePurchaseUseCase updatePurchaseUseCase;

    @Override
    public Purchase confirm(PaymentConfirmDTO dto) {
        var purchase = findPurchaseUseCase.getPurchase(dto.getData().getId());
        return updatePurchaseUseCase.updateStatus(purchase, PurchaseStatus.PAID);
    }
}
