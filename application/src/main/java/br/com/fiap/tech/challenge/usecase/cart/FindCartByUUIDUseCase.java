package br.com.fiap.tech.challenge.usecase.cart;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;

import java.util.UUID;

public interface FindCartByUUIDUseCase {

    Cart get(UUID uuid);

}