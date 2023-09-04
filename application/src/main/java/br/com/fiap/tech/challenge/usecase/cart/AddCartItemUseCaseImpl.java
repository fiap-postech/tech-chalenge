package br.com.fiap.tech.challenge.usecase.cart;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.enterprise.entity.CartItem;
import br.com.fiap.tech.challenge.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.gateway.CartWriterGateway;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
class AddCartItemUseCaseImpl implements AddCartItemUseCase {

    private CartReaderGateway cartReaderGateway;
    private CartWriterGateway writerService;

    @Override
    public Cart add(UUID uuid, CartItem item) {
        var cart = cartReaderGateway.readById(uuid)
                .addItem(item);
        return writerService.write(cart);
    }
}