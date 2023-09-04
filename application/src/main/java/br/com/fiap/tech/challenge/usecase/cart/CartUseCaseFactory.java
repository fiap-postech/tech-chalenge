package br.com.fiap.tech.challenge.usecase.cart;

import br.com.fiap.tech.challenge.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.usecase.customer.FindCustomerByUUIDUseCase;
import br.com.fiap.tech.challenge.usecase.product.FindProductByUUIDUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartUseCaseFactory {

    public static FindCartByUUIDUseCase findCartByUUIDService(CartReaderGateway reader) {
        return new FindCartByUUIDUseCaseImpl(reader);
    }

    public static CreateCartUseCase createCartService(FindCustomerByUUIDUseCase useCase, CartWriterGateway writer) {
        return new CreateCartUseCaseImpl(useCase, writer);
    }

    public static AddCartItemUseCase addCartItemService(CartReaderGateway reader, CartWriterGateway writer, FindProductByUUIDUseCase findProductByUUIDUseCase) {
        return new AddCartItemUseCaseImpl(reader, writer, findProductByUUIDUseCase);
    }

    public static UpdateCartItemUseCase updateCartItemService(CartReaderGateway reader, CartWriterGateway writer, FindProductByUUIDUseCase findProductByUUIDUseCase) {
        return new UpdateCartItemUseCaseImpl(reader, writer, findProductByUUIDUseCase);
    }

    public static RemoveCartItemUseCase removeCartItemUseCase(CartReaderGateway useCase, CartWriterGateway presenter) {
        return new RemoveCartItemUseCaseImpl(useCase, presenter);
    }
}
