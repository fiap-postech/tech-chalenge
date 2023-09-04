package br.com.fiap.tech.challenge.adapter.controller.purchase;

import br.com.fiap.tech.challenge.adapter.presenter.PurchasePresenter;
import br.com.fiap.tech.challenge.usecase.purchase.CheckoutUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseControllerFactory {

    public static CheckoutController checkoutController(CheckoutUseCase useCase, PurchasePresenter presenter) {
        return new CheckoutControllerImpl(useCase, presenter);
    }
}
