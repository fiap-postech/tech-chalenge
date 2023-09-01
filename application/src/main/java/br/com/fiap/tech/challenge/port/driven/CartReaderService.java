package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;

import java.util.UUID;

public interface CartReaderService {

    Cart readById(UUID id);

}