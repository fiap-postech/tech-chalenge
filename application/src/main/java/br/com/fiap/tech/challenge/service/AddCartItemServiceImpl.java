package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Cart;
import br.com.fiap.tech.challenge.domain.CartItem;
import br.com.fiap.tech.challenge.port.driven.CartReaderService;
import br.com.fiap.tech.challenge.port.driven.CartWriterService;
import br.com.fiap.tech.challenge.port.driver.AddCartItemService;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
class AddCartItemServiceImpl implements AddCartItemService {

    private CartReaderService cartReaderService;
    private CartWriterService writerService;

    @Override
    public Cart add(UUID uuid, CartItem item) {
        var cart = cartReaderService.readById(uuid)
                .addItem(item);
        return writerService.write(cart);
    }
}