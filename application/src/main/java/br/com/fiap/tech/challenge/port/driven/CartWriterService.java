package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.domain.Cart;
import br.com.fiap.tech.challenge.domain.CartItem;

import java.util.UUID;

public interface CartWriterService {

    Cart write(Cart cart);
    Cart saveItem(Cart cart, CartItem item);

    Cart deleteItem(UUID cartId, UUID cartItemId);
}