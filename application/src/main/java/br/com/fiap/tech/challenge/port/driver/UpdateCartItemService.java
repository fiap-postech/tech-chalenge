package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.enterprise.entity.CartItem;

import java.util.UUID;

public interface UpdateCartItemService {

    Cart update(UUID uuid, CartItem item);

}