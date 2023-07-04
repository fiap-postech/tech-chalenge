package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.domain.entity.Cart;
import br.com.fiap.tech.challenge.domain.entity.CartItem;

import java.util.UUID;

public interface AddCartItemService {

    Cart add(UUID uuid, CartItem item);

}