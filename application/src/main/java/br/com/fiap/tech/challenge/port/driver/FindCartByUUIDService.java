package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.domain.Cart;

import java.util.UUID;

public interface FindCartByUUIDService {

    Cart get(UUID uuid);

}