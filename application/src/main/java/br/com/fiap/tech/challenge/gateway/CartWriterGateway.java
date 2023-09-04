package br.com.fiap.tech.challenge.gateway;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;

public interface CartWriterGateway {

    Cart write(Cart cart);
}