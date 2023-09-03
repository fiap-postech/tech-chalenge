package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;

public interface CartWriterService {

    Cart write(Cart cart);
}