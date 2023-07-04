package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.domain.entity.Cart;
import br.com.fiap.tech.challenge.domain.entity.CartItem;

import java.util.UUID;

public interface RemoveCartItemService {

    Cart remove(UUID cartUuid, CartItem item);

}