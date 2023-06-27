package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.domain.Cart;

import java.util.UUID;

public interface RemoveCartItemService {

    Cart remove(UUID cartUuid, UUID cartItemUuid);

}