package br.com.fiap.tech.challenge.adapter.controller.cart;

import br.com.fiap.tech.challenge.adapter.presenter.CartPresenter;
import br.com.fiap.tech.challenge.usecase.cart.CreateCartUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartControllerFactory {

    public static CreateCartController createCartController(CreateCartUseCase useCase, CartPresenter presenter) {
        return new CreateCartControllerImpl(useCase, presenter);
    }

}
