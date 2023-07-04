package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.domain.Cart;

import java.util.UUID;

public interface CartReaderService {

    Cart readById(UUID id);

}