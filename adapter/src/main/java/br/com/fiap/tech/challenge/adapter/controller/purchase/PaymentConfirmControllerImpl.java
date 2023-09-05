package br.com.fiap.tech.challenge.adapter.controller.purchase;

import br.com.fiap.tech.challenge.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.adapter.presenter.PurchasePresenter;
import br.com.fiap.tech.challenge.dto.PaymentConfirmDTO;
import br.com.fiap.tech.challenge.usecase.purchase.PaymentConfirmUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PaymentConfirmControllerImpl implements PaymentConfirmController {

    private final PaymentConfirmUseCase useCase;
    private final PurchasePresenter presenter;

    @Override
    public PurchaseDTO confirm(PaymentConfirmDTO dto) {
        return presenter.present(useCase.confirm(dto));
    }
}
