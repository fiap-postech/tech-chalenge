package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Cart;
import br.com.fiap.tech.challenge.port.driven.CartWriterService;
import br.com.fiap.tech.challenge.port.driver.RemoveCartItemService;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class RemoveCartItemServiceImpl implements RemoveCartItemService {

    private CartWriterService writerService;

    @Override
    public Cart remove(UUID cartUuid, UUID cartItemUuid) {
        return writerService.deleteItem(cartUuid, cartItemUuid);
//        cart.removeItem(item);
    }
}