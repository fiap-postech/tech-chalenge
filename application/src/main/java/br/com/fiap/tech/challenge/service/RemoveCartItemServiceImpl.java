package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Cart;
import br.com.fiap.tech.challenge.domain.CartItem;
import br.com.fiap.tech.challenge.port.driven.CartReaderService;
import br.com.fiap.tech.challenge.port.driven.CartWriterService;
import br.com.fiap.tech.challenge.port.driver.RemoveCartItemService;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class RemoveCartItemServiceImpl implements RemoveCartItemService {

    private CartReaderService cartReaderService;
    private CartWriterService writerService;

    @Override
    public Cart remove(UUID uuid, CartItem item) {
        var cart = cartReaderService.readById(uuid)
                .removeItem(item);
        return writerService.write(cart);
    }
}