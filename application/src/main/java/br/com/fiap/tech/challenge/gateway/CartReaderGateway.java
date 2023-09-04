package br.com.fiap.tech.challenge.gateway;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;

import java.util.UUID;

public interface CartReaderGateway {

    Cart readById(UUID id);

}