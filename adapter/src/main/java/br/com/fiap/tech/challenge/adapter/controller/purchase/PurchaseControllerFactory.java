package br.com.fiap.tech.challenge.adapter.controller.purchase;

import br.com.fiap.tech.challenge.adapter.presenter.PurchasePresenter;
import br.com.fiap.tech.challenge.usecase.purchase.CheckoutUseCase;
import br.com.fiap.tech.challenge.usecase.purchase.FindAllPurchasesUseCase;
import br.com.fiap.tech.challenge.usecase.purchase.FindPurchaseByUUIDUseCase;
import br.com.fiap.tech.challenge.usecase.purchase.PaymentConfirmUseCase;
import br.com.fiap.tech.challenge.usecase.purchase.UpdatePurchaseStatusUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseControllerFactory {

    public static CheckoutController checkoutController(CheckoutUseCase useCase, PurchasePresenter presenter) {
        return new CheckoutControllerImpl(useCase, presenter);
    }

    public static PaymentConfirmController paymentConfirmController(PaymentConfirmUseCase useCase, PurchasePresenter presenter) {
        return new PaymentConfirmControllerImpl(useCase, presenter);
    }

    public static UpdatePurchaseStatusController updatePurchaseStatusController(UpdatePurchaseStatusUseCase useCase, PurchasePresenter presenter) {
        return new UpdatePurchaseStatusControllerImpl(useCase, presenter);
    }

    public static FindPurchaseByUUIDController findPurchaseByUUIDController(FindPurchaseByUUIDUseCase useCase, PurchasePresenter presenter) {
        return new FindPurchaseByUUIDControllerImpl(useCase, presenter);
    }

    public static FindAllPurchasesController findAllPurchasesController(FindAllPurchasesUseCase useCase, PurchasePresenter presenter) {
        return new FindAllPurchasesControllerImpl(useCase, presenter);
    }
}
