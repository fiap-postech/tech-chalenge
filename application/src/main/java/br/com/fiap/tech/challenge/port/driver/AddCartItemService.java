package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.domain.Cart;
import br.com.fiap.tech.challenge.domain.CartItem;

import java.util.UUID;

public interface AddCartItemService {

    Cart add(UUID uuid, CartItem item);

}