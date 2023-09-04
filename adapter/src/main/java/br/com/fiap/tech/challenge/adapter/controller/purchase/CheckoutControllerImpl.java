package br.com.fiap.tech.challenge.adapter.controller.purchase;

import br.com.fiap.tech.challenge.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.adapter.presenter.PurchasePresenter;
import br.com.fiap.tech.challenge.usecase.purchase.CheckoutUseCase;
import lombok.RequiredArgsConstructor;

import static java.util.UUID.fromString;

@RequiredArgsConstructor
class CheckoutControllerImpl implements CheckoutController {

    private final CheckoutUseCase useCase;
    private final PurchasePresenter presenter;


    @Override
    public PurchaseDTO checkout(String cartId) {
        return presenter.present(useCase.checkout(fromString(cartId)));
    }
}
