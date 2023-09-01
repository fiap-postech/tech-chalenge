package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.enterprise.entity.CartItem;
import br.com.fiap.tech.challenge.port.driven.CartReaderService;
import br.com.fiap.tech.challenge.port.driven.CartWriterService;
import br.com.fiap.tech.challenge.port.driver.UpdateCartItemService;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
class UpdateCartItemServiceImpl implements UpdateCartItemService {

    private CartReaderService cartReaderService;
    private CartWriterService writerService;

    @Override
    public Cart update(UUID uuid, CartItem item) {
        var cart = cartReaderService.readById(uuid)
                .updateItem(item);
        return writerService.write(cart);
    }
}