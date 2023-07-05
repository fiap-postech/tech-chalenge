package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.domain.entity.Cart;

public interface CartWriterService {

    Cart write(Cart cart);
}