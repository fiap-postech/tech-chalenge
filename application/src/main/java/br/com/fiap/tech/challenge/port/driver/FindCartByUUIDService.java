package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;

import java.util.UUID;

public interface FindCartByUUIDService {

    Cart get(UUID uuid);

}