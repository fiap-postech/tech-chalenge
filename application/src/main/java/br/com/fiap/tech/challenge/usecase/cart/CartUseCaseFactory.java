package br.com.fiap.tech.challenge.usecase.cart;

import br.com.fiap.tech.challenge.port.driven.CartReaderService;
import br.com.fiap.tech.challenge.port.driven.CartWriterService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartUseCaseFactory {

    public static FindCartByUUIDUseCase findCartByUUIDService(CartReaderService reader) {
        return new FindCartByUUIDUseCaseImpl(reader);
    }

    public static CreateCartUseCase createCartService(CartWriterService writer) {
        return new CreateCartUseCaseImpl(writer);
    }

    public static AddCartItemUseCase addCartItemService(CartReaderService reader, CartWriterService writer) {
        return new AddCartItemUseCaseImpl(reader, writer);
    }

    public static UpdateCartItemUseCase updateCartItemService(CartReaderService reader, CartWriterService writer) {
        return new UpdateCartItemUseCaseImpl(reader, writer);
    }
}
