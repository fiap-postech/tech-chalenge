package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Cart;
import br.com.fiap.tech.challenge.domain.CartItem;
import br.com.fiap.tech.challenge.port.driven.CartReaderService;
import br.com.fiap.tech.challenge.port.driven.CartWriterService;
import br.com.fiap.tech.challenge.port.driver.AddCartItemService;
import br.com.fiap.tech.challenge.port.driver.FindProductByUUIDService;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class AddCartItemServiceImpl implements AddCartItemService {

    private CartReaderService cartReaderService;
    private CartWriterService writerService;
    private FindProductByUUIDService findProductByUUIDService;

    @Override
    public Cart add(UUID uuid, CartItem item) {
//        var cart = cartReaderService.readById(uuid);
//        findProductByUUIDService.get(item.product().uuid());
//        return writerService.saveItem(cart, item);



//        cart.addItem(item);
//        return cart;

        return null;
    }
}