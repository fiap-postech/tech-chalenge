package br.com.fiap.tech.challenge.usecase.cart;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.enterprise.entity.CartItem;
import br.com.fiap.tech.challenge.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.gateway.CartWriterGateway;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
class UpdateCartItemUseCaseImpl implements UpdateCartItemUseCase {

    private CartReaderGateway cartReaderGateway;
    private CartWriterGateway writerService;

    @Override
    public Cart update(UUID uuid, CartItem item) {
        var cart = cartReaderGateway.readById(uuid)
                .updateItem(item);
        return writerService.write(cart);
    }
}