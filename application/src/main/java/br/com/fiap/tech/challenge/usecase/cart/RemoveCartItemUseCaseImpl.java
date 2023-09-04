package br.com.fiap.tech.challenge.usecase.cart;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.enterprise.entity.CartItem;
import br.com.fiap.tech.challenge.port.driven.CartReaderService;
import br.com.fiap.tech.challenge.port.driven.CartWriterService;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class RemoveCartItemUseCaseImpl implements RemoveCartItemUseCase {

    private CartReaderService cartReaderService;
    private CartWriterService writerService;

    @Override
    public Cart remove(UUID uuid, CartItem item) {
        var cart = cartReaderService.readById(uuid)
                .removeItem(item);
        return writerService.write(cart);
    }
}