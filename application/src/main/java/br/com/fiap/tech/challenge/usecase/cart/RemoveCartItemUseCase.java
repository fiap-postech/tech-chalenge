package br.com.fiap.tech.challenge.usecase.cart;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.enterprise.entity.CartItem;

import java.util.UUID;

public interface RemoveCartItemUseCase {

    Cart remove(UUID cartUuid, CartItem item);

}