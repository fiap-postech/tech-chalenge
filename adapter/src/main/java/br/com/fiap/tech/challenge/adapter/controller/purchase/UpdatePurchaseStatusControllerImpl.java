package br.com.fiap.tech.challenge.adapter.controller.purchase;

import br.com.fiap.tech.challenge.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.adapter.presenter.PurchasePresenter;
import br.com.fiap.tech.challenge.enterprise.enums.PurchaseStatus;
import br.com.fiap.tech.challenge.application.usecase.purchase.UpdatePurchaseStatusUseCase;
import lombok.RequiredArgsConstructor;

import static java.util.UUID.fromString;

@RequiredArgsConstructor
class UpdatePurchaseStatusControllerImpl implements UpdatePurchaseStatusController {

    private final UpdatePurchaseStatusUseCase useCase;
    private final PurchasePresenter presenter;

    @Override
    public PurchaseDTO update(String purchaseUUID, PurchaseStatus status) {
        return presenter.present(useCase.update(fromString(purchaseUUID), status));
    }
}
